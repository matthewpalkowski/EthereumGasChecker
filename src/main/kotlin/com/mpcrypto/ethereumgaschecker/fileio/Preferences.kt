package com.mpcrypto.ethereumgaschecker.fileio

import kotlinx.serialization.Serializable

/**
 * Data class the maps onto the values of user preferences for use in Gson conversions.
 */
@Serializable
data class Preferences(
    var durationThreshold : Int,
    var gasThreshold : Double,
    var scanFrequency : Int)