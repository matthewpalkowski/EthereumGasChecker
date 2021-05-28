package com.mpcrypto.ethereumgaschecker.states

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import javafx.scene.control.Button

class ActiveState : ProgramState() {

    override fun setButtonText(button : Button) {button.text = StringConstants.BUTTON_SCAN_TEXT_STOP}

    override fun managePriceScanning() {
        TODO("Not yet implemented")
    }
}