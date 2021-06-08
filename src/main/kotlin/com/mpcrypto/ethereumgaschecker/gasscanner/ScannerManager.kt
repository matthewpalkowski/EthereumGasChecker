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

    private fun changeState(){
        currentGasState = if(currentGasState == aboveState) belowState
        else aboveState
    }

    fun startScanning() {
        scanner.attachObserver(this)
        scanningThread = threadScope.launch {
            while(true){
                scanner.queryGas()
                delay(PreferencesManager.getFrequencyMillis())
            }
        }
    }

    fun stopScanning(){
        scanner.detachObserver(this)
        scanningThread!!.cancel()
    }

    override fun update(value: Double) {
        GasHistory.addSnapshot(GasSnapshot(value, System.currentTimeMillis()))
        if(currentGasState.validateSnapshot()){
            currentGasState.notifyUser()
            changeState()
        }
    }
}