package com.mpcrypto.ethereumgaschecker.fileio

data class Preferences(
    var durationThreshold : Int,
    var gasThreshold : Double,
    var scanFrequency : Int)