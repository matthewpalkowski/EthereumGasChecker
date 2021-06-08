package com.mpcrypto.ethereumgaschecker.gui

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import com.mpcrypto.ethereumgaschecker.gasscanner.ScannerManager
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
    private var scanningActive: Boolean = false

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
        btnGasScanning.setOnMouseClicked {
            toggleScanning()

        }
        btnSetPreferences.setOnMouseClicked {replaceWith<PreferencesView>()}
    }

    private fun toggleScanning(){
        if(scanningActive){
            btnGasScanning.text = StringConstants.BUTTON_SCAN_TEXT_STOP
            scannerManager.stopScanning()
        }
        else{
            btnGasScanning.text = StringConstants.BUTTON_SCAN_TEXT_START
            scannerManager.startScanning()
        }
    }
}
