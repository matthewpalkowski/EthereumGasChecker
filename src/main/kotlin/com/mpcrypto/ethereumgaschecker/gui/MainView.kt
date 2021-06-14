package com.mpcrypto.ethereumgaschecker.gui

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import com.mpcrypto.ethereumgaschecker.gasscanner.ScannerManager
import com.mpcrypto.ethereumgaschecker.observerutils.IValueObserver
import javafx.application.Platform
import javafx.scene.control.*
import javafx.scene.layout.AnchorPane
import tornadofx.*

/**
 * Controller for the MainView view.
 */
class MainView : View(StringConstants.APPLICATION_NAME), IValueObserver {

    //TODO Should add a check box to designate whether user wants to be notified view tray message
    //TODO Need to add small animation to show program is active

    //FXML Variables
    override val root : AnchorPane by fxml(StringConstants.PATH_MAIN_FXML,true)
    private lateinit var lblCurrentPrice : Label
    private lateinit var btnGasScanning : Button
    private lateinit var btnSetPreferences : Button

    //Utilities
    private val scannerManager = ScannerManager()

    init {
        for(child in root.children){
            when(child.id){
                StringConstants.BUTTON_SCAN_ID ->btnGasScanning = child as Button
                StringConstants.BUTTON_SET_PREFERENCES_ID -> btnSetPreferences = child as Button
                StringConstants.LABEL_PRICE_ID -> lblCurrentPrice = child as Label
            }
        }
        btnGasScanning.setOnMouseClicked {toggleScanning()}
        btnSetPreferences.setOnMouseClicked {replaceWith<PreferencesView>()}
    }

    private fun toggleScanning(){
        if(scannerManager.isActive()){
            btnGasScanning.text = StringConstants.BUTTON_SCAN_TEXT_START
            scannerManager.stopScanning(this)
        }
        else{
            btnGasScanning.text = StringConstants.BUTTON_SCAN_TEXT_STOP
            scannerManager.startScanning(this)
        }
    }

    override fun update(value: Int) {Platform.runLater {lblCurrentPrice.text = value.toString()}}
}
