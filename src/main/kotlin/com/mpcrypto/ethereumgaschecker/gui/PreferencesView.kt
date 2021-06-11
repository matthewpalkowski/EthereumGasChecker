package com.mpcrypto.ethereumgaschecker.gui

import com.mpcrypto.ethereumgaschecker.constants.*
import com.mpcrypto.ethereumgaschecker.fileio.*
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.control.TextFormatter
import javafx.scene.layout.AnchorPane
import tornadofx.*

/**
 * Controller for the PreferencesView view.
 */
class PreferencesView : View(StringConstants.APPLICATION_NAME) {

    //TODO Add warning messages for when user tries to enter invalid input

    //FXML Variables
    override val root: AnchorPane by fxml(StringConstants.PATH_PREFERENCES_FXML)
    private lateinit var btnAccept : Button
    private lateinit var btnCancel : Button
    private lateinit var txtDurationThreshold : TextField
    private lateinit var txtGasThreshold : TextField
    private lateinit var txtScanFrequency : TextField

    init {
        //Cache references to UI components
        for(child in root.children){
            when(child.id){
                StringConstants.BUTTON_ACCEPT_ID -> {btnAccept = child as Button}
                StringConstants.BUTTON_CANCEL_ID -> {btnCancel = child as Button}
                StringConstants.TEXTFIELD_DURATION_THRESHOLD -> {txtDurationThreshold = child as TextField}
                StringConstants.TEXTFIELD_GAS_THRESHOLD -> {txtGasThreshold = child as TextField}
                StringConstants.TEXTFIELD_SCAN_FREQUENCY -> {txtScanFrequency = child as TextField}
            }
        }

        //Initialize state of control to buttons and text fields
        populateValues()
        assignButtonListeners()
        assignInputFilters()
    }

    private fun assignButtonListeners(){
        btnAccept.setOnMouseClicked {
            updatePreferences()
            returnToMainView()
        }

        btnCancel.setOnMouseClicked {
            returnToMainView()
        }
    }

    private fun assignInputFilters(){
        txtDurationThreshold.filterInput { change -> validateInput(change)}
        txtScanFrequency.filterInput {change -> validateInput(change)}
        txtGasThreshold.filterInput { change ->
            !change.isAdded ||
                (change.controlNewText.let{it.isDouble() && it.toDouble() < NumericalConstants.MAX_GAS}) ||
                (txtDurationThreshold.caretPosition == txtDurationThreshold.length-1
                        && change.text.length == 1 && change.text!!.contentEquals("."))
            //FIXME have to limit allowable # of chars after the decimal
        }
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

    private fun validateInput(change : TextFormatter.Change) : Boolean{
        return !change.isAdded || change.controlNewText.let{it.isInt() && it.toInt() in 1..NumericalConstants.MAX_DURATION_THRESHOLD}
    }
}
