package com.mpcrypto.ethereumgaschecker.states.programstates

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import com.mpcrypto.ethereumgaschecker.fileio.PreferencesManager
import com.mpcrypto.ethereumgaschecker.gasscanner.*
import com.mpcrypto.ethereumgaschecker.gasscanner.etherscan.*
import com.mpcrypto.ethereumgaschecker.states.valuethresholdstates.*
import javafx.scene.control.Button

class ActiveState() : IProgramState, GasValueObserver(){

    /*TODO
    *   -Observe when preferences change
    *   -Make sure that values are sent with time stamps
    * */

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
        //Append value to running list (list should be as long as maximum allowable time steps * min query
        //Start from the most recent item, check that its value is above//below the threshold, store the first timestamp
        //Repeat going back through the list until one of the following conditions is met:
        //  -a disqualifying value is found,
        //  -the timestamp is beyond the duration threshold is found
        //      -Then notify user and change state
        //  -End of the list encountered
    }
}