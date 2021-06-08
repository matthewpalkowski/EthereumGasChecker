package com.mpcrypto.ethereumgaschecker.gui

import com.mpcrypto.ethereumgaschecker.constants.*
import com.mpcrypto.ethereumgaschecker.fileio.*
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import tornadofx.*

/**
 * Controller for the PreferencesView view.
 */
class PreferencesView : View(StringConstants.APPLICATION_NAME) {

    //TODO Limit inputs for text fields

    //FXML Variables
    override val root: AnchorPane by fxml(StringConstants.PATH_PREFERENCES_FXML)
    private lateinit var btnAccept : Button
    private lateinit var btnCancel : Button
    private lateinit var txtDurationThreshold : TextField
    private lateinit var txtGasThreshold : TextField
    private lateinit var txtScanFrequency : TextField

    init {
        for(child in root.children){
            when(child.id){
                StringConstants.BUTTON_ACCEPT_ID -> {btnAccept = child as Button}
                StringConstants.BUTTON_CANCEL_ID -> {btnCancel = child as Button}
                StringConstants.TEXTFIELD_DURATION_THRESHOLD -> {txtDurationThreshold = child as TextField}
                StringConstants.TEXTFIELD_GAS_THRESHOLD -> {txtGasThreshold = child as TextField}
                StringConstants.TEXTFIELD_SCAN_FREQUENCY -> {txtScanFrequency = child as TextField}
            }
        }

        populateValues()

        btnAccept.setOnMouseClicked {
            updatePreferences()
            returnToMainView()
        }

        btnCancel.setOnMouseClicked {
            returnToMainView()
        }

//        txtDurationThreshold.filterInput { change -> !change.isAdded || change.controlNewText.let(
//            change.isInt() && it.toInt in 1..NumericalConstants.DAY_IN_SECONDS)
//        }
//        txtGasThreshold.filterInput {  }
//        txtScanFrequency.filterInput {  }

    }

    private fun populateValues(){
        val currentPrefs : Preferences = PreferencesManager.getPreferences()
        txtDurationThreshold.text = currentPrefs.durationThreshold.toString()
        txtGasThreshold.text = currentPrefs.gasThreshold.toString()
        txtScanFrequency.text = currentPrefs.scanFrequency.toString()
    }

    private fun returnToMainView(){replaceWith<MainView>()}

    //TODO Make below a FileIO thread and make associated components thread-safe
    private fun updatePreferences(){
        val prefs = PreferencesManager.getPreferences()
        //TODO - Have to validate that inputs are valid
        prefs.durationThreshold = txtDurationThreshold.text.toInt()
        prefs.gasThreshold = txtGasThreshold.text.toDouble()
        prefs.scanFrequency = txtScanFrequency.text.toInt()
        PreferencesManager.updatePreferences()
    }
}
