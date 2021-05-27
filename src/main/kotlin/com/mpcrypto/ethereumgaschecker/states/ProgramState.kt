package com.mpcrypto.ethereumgaschecker.states

import javafx.scene.control.Button

abstract class ProgramState {

    abstract fun setButtonText(button : Button)

    abstract fun managePriceScanning()

    fun manageUserPreferences(){

        /*TODO
         *  -Open a new window on thread separate from scanning thread
         *  -Wait get response from that window
         *  -If user changes values - manage threads accordingly
         */
    }
}