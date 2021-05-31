package com.mpcrypto.ethereumgaschecker.states

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import com.mpcrypto.ethereumgaschecker.gasscanner.*
import com.mpcrypto.ethereumgaschecker.gasscanner.etherscan.*
import javafx.scene.control.Button

class ActiveState() : IProgramState, GasValueObserver(){

    private lateinit var scanner : GasScanner

    override fun setButtonText(button : Button) {button.text = StringConstants.BUTTON_SCAN_TEXT_STOP}

    override fun managePriceScanning() {
        scanner = EtherscanScanner()
        scanner.queryGas()
    }

    override fun update(value: Double) {
        TODO("Not yet implemented")
    }
}