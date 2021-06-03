package com.mpcrypto.ethereumgaschecker.states.programstates

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import com.mpcrypto.ethereumgaschecker.gasscanner.*
import com.mpcrypto.ethereumgaschecker.gasscanner.etherscan.*
import com.mpcrypto.ethereumgaschecker.observerutils.IValueObserver
import com.mpcrypto.ethereumgaschecker.states.valuethresholdstates.*
import javafx.scene.control.Button

class ActiveState() : IProgramState, IValueObserver {

    private val aboveState : IGasThresholdState = AboveThresholdState()
    private val belowState : IGasThresholdState = BelowThresholdState()

    private lateinit var scanner : GasScanner
    private var currentGasState : IGasThresholdState = belowState

    private fun changeState(){
        currentGasState = if(currentGasState == aboveState) belowState
        else aboveState
    }

    override fun managePriceScanning() {
        scanner = EtherscanScanner()
        scanner.queryGas()
    }

    override fun setButtonText(button : Button) {button.text = StringConstants.BUTTON_SCAN_TEXT_STOP}

    override fun update(value: Double) {
        GasHistory.addSnapshot(GasSnapshot(value, System.currentTimeMillis()))
        if(currentGasState.validateSnapshot()){
            currentGasState.notifyUser()
            changeState()
        }
    }
}