package com.mpcrypto.ethereumgaschecker.states.programstates

import javafx.scene.control.Button

interface IProgramState {

    fun setButtonText(button : Button)

    fun managePriceScanning()
}