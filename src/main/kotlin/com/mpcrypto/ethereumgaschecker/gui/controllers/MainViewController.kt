package com.mpcrypto.ethereumgaschecker.gui.controllers

import com.mpcrypto.ethereumgaschecker.states.*
import javafx.beans.property.SimpleStringProperty
import tornadofx.Controller

class MainViewController : Controller() {

    private var currentState : ProgramState = IdleState()

    private val idleState = IdleState()
    private val activeState = ActiveState()

    init {
        currentState = idleState
    }

    private fun changeState(){
        currentState = if(currentState == idleState) activeState
        else idleState
    }

    fun scanClicked(stringProperty : SimpleStringProperty){
        changeState()
        currentState.setStringPropertyVal(stringProperty)
        //TODO add logic for api queries and threading - likely should be passing around a thread object to the state
    }

    fun preferencesClicked(){currentState.manageUserPreferences()}
}