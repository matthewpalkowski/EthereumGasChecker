package com.mpcrypto.ethereumgaschecker.states

import constants.StringConstants
import javafx.scene.control.Button

class IdleState : ProgramState() {

    override fun setButtonText(button : Button) {button.text = StringConstants.BUTTON_SCAN_TEXT_START}

    override fun managePriceScanning() {
        TODO("Not yet implemented")
    }
}