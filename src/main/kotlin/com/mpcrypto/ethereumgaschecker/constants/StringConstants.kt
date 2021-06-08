package com.mpcrypto.ethereumgaschecker.constants

/**
 * Class to serve as a global access point for string constants.
 */
class StringConstants {
    companion object{
        //API constants derived from the Etherscan.Etherscan API (https://etherscan.io/apis#proxy)
        const val ETHERSCAN_ACTION: String = "eth_gasPrice"
        const val ETHERSCAN_BASE_URL: String = "https://api.etherscan.io/"
        const val ETHERSCAN_GAS_MODULE: String = "proxy"
        const val ETHERSCAN_KEY_ACTION: String = "action"
        const val ETHERSCAN_KEY_MODULE: String = "module"
        const val ETHERSCAN_KEY_APIKEY: String = "apikey"

        //GUI Display Text
        const val APPLICATION_NAME = "Ethereum Gas Checker"
        const val BUTTON_SCAN_TEXT_START = "Scan gas prices"
        const val BUTTON_SCAN_TEXT_STOP = "Stop"
        const val LABEL_PRICE_DEFAULT_TEXT = "UNK"

        //TODO consider removing values since they are specified in FXML file manually
        const val BUTTON_SET_PREFERENCES_TEXT = "Set Preferences"
        const val LABEL_DURATION_THRESHOLD_TEXT = "Duration threshold"
        const val LABEL_GAS_THRESHOLD_TEXT = "Gas threshold"
        const val LABEL_SCAN_FREQUENCY_TEXT = "Gas price scan frequency (seconds)"

        //GUI Component IDs
        const val BUTTON_ACCEPT_ID = "btnAccept"
        const val BUTTON_CANCEL_ID = "btnCancel"
        const val BUTTON_SCAN_ID = "btnGasScanning"
        const val BUTTON_SET_PREFERENCES_ID = "btnSetPreferences"
        const val LABEL_PRICE_ID = "lblCurrentPrice"
        const val TEXTFIELD_DURATION_THRESHOLD = "txtDuration"
        const val TEXTFIELD_GAS_THRESHOLD = "txtGas"
        const val TEXTFIELD_SCAN_FREQUENCY = "txtFrequency"

        //Filepaths
        const val PATH_ICON = "drawable/AppIcon.png"
        const val PATH_MAIN_FXML = "/layout/MainView.fxml"
        const val PATH_PREFERENCES =  "values/UserPreferences.json"
        const val PATH_PREFERENCES_FXML = "/layout/PreferencesView.fxml"

        //Notification Messages
        const val MESSAGE_ABOVE_THRESHOLD = "Ethereum gas prices have risen above threshold"
        const val MESSAGE_BELOW_THRESHOLD = "Ethereum gas prices have fallen below threshold"

        //Notification Strings
        const val ID_ICON = "App_Icon"

        //Thread Names
        const val SCANNING_THREAD = "GasScanningScope"
    }
}