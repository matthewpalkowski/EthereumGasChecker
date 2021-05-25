package com.mpcrypto.ethereumgaschecker.states

import javafx.beans.property.SimpleStringProperty

abstract class ProgramState {

    abstract fun setStringPropertyVal(stringProperty : SimpleStringProperty)

    fun manageUserPreferences(){

        /*TODO
         *  -Open a new window on thread separate from scanning thread
         *  -Wait get response from that window
         *  -If user changes values - manage threads accordingly
         */
    }
}