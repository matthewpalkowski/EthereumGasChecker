package com.mpcrypto.ethereumgaschecker.gui

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import com.mpcrypto.ethereumgaschecker.states.*
import javafx.scene.control.*
import javafx.scene.layout.AnchorPane
import tornadofx.*

class MainView : View(StringConstants.APPLICATION_NAME) {

    //FXML Variables
    override val root : AnchorPane by fxml(StringConstants.PATH_MAIN_FXML,true)
    private lateinit var lblCurrentPrice : Label
    private lateinit var btnGasScanning : Button
    private lateinit var btnSetPreferences : Button

    //State Variables
    private var currentState : ProgramState
    private val idleState = IdleState()
    private val activeState = ActiveState()

    init {
        currentState = idleState
        for(child in root.children){
            when(child.id){
                StringConstants.BUTTON_SCAN_ID ->btnGasScanning = child as Button
                StringConstants.BUTTON_SET_PREFERENCES_ID -> btnSetPreferences = child as Button
                StringConstants.LABEL_PRICE_ID -> lblCurrentPrice = child as Label
            }
        }
        btnGasScanning.setOnMouseClicked {currentState.setButtonText(btnGasScanning)}
        btnSetPreferences.setOnMouseClicked { currentState.manageUserPreferences()}
    }

    private fun changeState(){
        currentState = if(currentState == idleState) activeState
        else idleState
    }
}
