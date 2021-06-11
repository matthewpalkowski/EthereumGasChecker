package com.mpcrypto.ethereumgaschecker.gasscanner

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import com.mpcrypto.ethereumgaschecker.fileio.PreferencesManager
import com.mpcrypto.ethereumgaschecker.gasscanner.etherscan.*
import com.mpcrypto.ethereumgaschecker.observerutils.IValueObserver
import com.mpcrypto.ethereumgaschecker.states.*
import kotlinx.coroutines.*

/**
 * Class to manage gas price scanning and to trigger notifications to the user when conditions specified by the user
 * in preferences are met.
 */
class ScannerManager() : IValueObserver {

    //TODO add labels
    private val aboveState = AboveThresholdState()
    private val belowState = BelowThresholdState()
    private val scanner = EtherscanScanner() //TODO abstract as required if it can be generalized to other forms of scanners
    private val threadScope = CoroutineScope(CoroutineName(StringConstants.SCANNING_THREAD))

    private var currentGasState : GasThresholdState = belowState
    private var scanningThread : Job? = null

    private var active = false

    private fun changeState(){
        currentGasState = if(currentGasState == aboveState) belowState
        else aboveState
    }

    fun isActive(): Boolean{return active}

    private fun launchScan(){
        scanningThread = threadScope.launch {
            while(true){
                scanner.queryGas()
                delay(PreferencesManager.getFrequencyMillis())
            }
        }
    }

    fun startScanning(){
        scanner.attachObserver(this)
        launchScan()
    }

    fun startScanning(valueObserver : IValueObserver){
        scanner.attachObserver(valueObserver)
        scanner.attachObserver(this)
        launchScan()
    }

    fun startScanning(valueObservers: MutableList<IValueObserver>?) {
        active = true
        valueObservers!!.add(this)
        for (observer in valueObservers) scanner.attachObserver(observer)
        launchScan()
    }

    fun stopScanning(){
        scanner.detachObserver(this)
        teardownScan()
    }

    fun stopScanning(valueObserver: IValueObserver){
        scanner.detachObserver(valueObserver)
        scanner.detachObserver(this)
        teardownScan()
    }

    fun stopScanning(valueObservers: MutableList<IValueObserver>){
        valueObservers.add(this)
        for (observer in valueObservers) scanner.detachObserver(observer)
        teardownScan()
    }

    private fun teardownScan(){
        active = false
        scanningThread!!.cancel()
    }

    override fun update(value: Int) {
        GasHistory.addSnapshot(GasSnapshot(value, System.currentTimeMillis()))
        if(currentGasState.validateSnapshot()){
            changeState()
            currentGasState.notifyUser()
        }
    }
}