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

    //TODO consider changing MutableLists into regular static lists within method bodies.

    //State constants
    private val aboveState = AboveThresholdState()
    private val belowState = BelowThresholdState()

    //Constants for Etherscan queries.
    private val scanner = EtherscanScanner() //TODO abstract as required if it can be generalized to other forms of scanners
    private val threadScope = CoroutineScope(CoroutineName(StringConstants.SCANNING_THREAD))

    //Runtime variables tracking scanning and gas value states
    private var currentGasState : GasThresholdState = belowState
    private var scanningThread : Job? = null
    private var active = false

    private fun changeState(){
        println("State changed")
        currentGasState = if(currentGasState == aboveState) belowState
        else aboveState
    }

    /**
     * Function that returns whether the scanning thread is currently active.
     * @return true if thread is active, otherwise false.
     */
    fun isActive(): Boolean{return active}

    private fun launchScan(){
        this.active = true
        scanningThread = threadScope.launch {
            while(true){
                scanner.queryGas()
                delay(PreferencesManager.getFrequencyMillis())
            }
        }
    }

    /**
     * Function to begin scan of gas value.
     */
    fun startScanning(){
        scanner.attachObserver(this)
        launchScan()
    }

    /**
     * Function to begin scan of gas value. Adds valueObserver to the list of observers for returns of the scan.
     * @param valueObserver - IValueObserver to be passed to scanner as an observer.
     */
    fun startScanning(valueObserver : IValueObserver){
        scanner.attachObserver(valueObserver)
        scanner.attachObserver(this)
        launchScan()
    }

    /**
     * Function to begin scan of gas value. Adds list of IValueObservers to the list of observers for returns of the
     * scan.
     * @param valueObservers - MutableList of IValueObserver to be passed to scanner as observers.
     */
    fun startScanning(valueObservers: MutableList<IValueObserver>?) {
        active = true
        valueObservers!!.add(this)
        for (observer in valueObservers) scanner.attachObserver(observer)
        launchScan()
    }

    /**
     * Function to cease and active scan and detach observers of the scan.
     */
    fun stopScanning(){
        scanner.detachObserver(this)
        teardownScan()
    }

    /**
     * Function to cease and active scan and detach observers of the scan, including valueObserver.
     * @param valueObserver - IValueObservers that is to be passed to scanner to be detached as an observer.
     */
    fun stopScanning(valueObserver: IValueObserver){
        scanner.detachObserver(valueObserver)
        scanner.detachObserver(this)
        teardownScan()
    }


    /**
     * Function to cease and active scan and detach observers of the scan, including items inside of valueObservers.
     * @param valueObservers - MutableList of IValueObservers that are to be passed to scanner and detached as observers.
     */
    fun stopScanning(valueObservers: MutableList<IValueObserver>){
        valueObservers.add(this)
        for (observer in valueObservers) scanner.detachObserver(observer)
        teardownScan()
    }

    private fun teardownScan(){
        active = false
        scanningThread!!.cancel()
    }

    /**
     * Update function for the Observer Patterned used in monitoring of gas value. Triggered when new value
     * received. When triggered, state is modified accordingly and notification triggered.
     * @param value - Int broadcast by the IValueObservable being observed.
     */
    override fun update(value: Int) {
        GasHistory.addSnapshot(GasSnapshot(value, System.currentTimeMillis()))
        if(currentGasState.validateSnapshot()){
            changeState()
            currentGasState.notifyUser()
        }
    }
}