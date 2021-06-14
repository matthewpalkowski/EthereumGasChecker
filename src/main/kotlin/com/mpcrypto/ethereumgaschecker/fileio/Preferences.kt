package com.mpcrypto.ethereumgaschecker.fileio

/**
 * Data class the maps onto the values of user preferences for use in Gson conversions.
 */
data class Preferences(
    var durationThreshold : Int,
    var gasThreshold : Int,
    var scanFrequency : Int)