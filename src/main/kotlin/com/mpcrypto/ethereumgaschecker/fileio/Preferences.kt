package com.mpcrypto.ethereumgaschecker.fileio

data class Preferences(
    val GasThreshold : Double,
    val ScanFrequency : Int,
    val DurationThreshold : Int)