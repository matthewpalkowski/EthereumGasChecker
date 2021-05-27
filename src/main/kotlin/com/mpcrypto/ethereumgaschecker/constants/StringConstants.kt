package com.mpcrypto.ethereumgaschecker.constants

//TODO Move to resources package & change below references to a getResource call
class StringConstants {
    companion object{
        //API constants derived from the Etherscan.Etherscan API (https://etherscan.io/apis#proxy)
        const val ETHERSCAN_ACTION: String = "eth_gasPrice"
        const val ETHERSCAN_BASE_URL: String = "https://api.etherscan.io/api"
        const val ETHERSCAN_GAS_MODULE: String = "proxy"

        //GUI Display Text
        const val APPLICATION_NAME = "Ethereum Gas Checker"
        const val BUTTON_SCAN_TEXT_START = "Scan gas prices"
        const val BUTTON_SCAN_TEXT_STOP = "Stop"
        const val BUTTON_SET_PREFERENCES_TEXT = "Set Preferences"
        const val LABEL_DURATION_THRESHOLD_TEXT = "Duration threshold"
        const val LABEL_GAS_THRESHOLD_TEXT = "Gas threshold"
        const val LABEL_PRICE_DEFAULT_TEXT = "UNK"
        const val LABEL_SCAN_FREQUENCY_TEXT = "Gas price scan frequency (seconds)"

        //GUI Component IDs
        const val BUTTON_ACCEPT_ID = "btnAccept"
        const val CANCEL_BUTTON_ID = "btnCancel"
        const val BUTTON_SCAN_ID = "btnGasScanning"
        const val BUTTON_SET_PREFERENCES_ID = "btnSetPreferences"
        const val LABEL_PRICE_ID = "lblCurrentPrice"
        const val TEXTFIELD_DURATION_THRESHOLD = "txtDuration"
        const val TEXTFIELD_GAS_THRESHOLD = "txtGas"
        const val TEXTFIELD_SCAN_FREQUENCY = "txtFrequency"

        //Filepaths
        const val PATH_PREFERENCES =  "UserPreferences.json"
        const val PATH_MAIN_FXML = "/layout/MainView.fxml"
    }
}