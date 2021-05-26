package com.mpcrypto.ethereumgaschecker.constants

//TODO Move to resources package & change below references to a getResource call
class StringConstants {
    companion object{
        //API constants derived from the Etherscan.Etherscan API (https://etherscan.io/apis#proxy)
        const val ETHERSCAN_ACTION: String = "eth_gasPrice"
        const val ETHERSCAN_BASE_URL: String = "https://api.etherscan.io/api"
        const val ETHERSCAN_GAS_MODULE: String = "proxy"

        //Other application constants
        const val APPLICATION_NAME = "Ethereum Gas Checker"
        const val SET_PREFERENCES_BUTTON = "Set Preferences"
        const val GAS_THRESHOLD_LABEL = "Gas threshold"
        const val SCAN_FREQUENCY_LABEL = "Gas price scan frequency (seconds)"
        const val DURATION_THRESHOLD_LABEL = "Duration threshold"
        const val START_BUTTON = "Scan gas prices"
        const val STOP_BUTTON = "Stop"

        //Filepaths
        const val PREFERENCE_FILE =  "UserPreferences.json"
        const val MAIN_FXML = "/layout/MainView.fxml"
    }
}