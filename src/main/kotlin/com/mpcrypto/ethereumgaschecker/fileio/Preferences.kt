package com.mpcrypto.ethereumgaschecker.fileio

data class Preferences(
    val durationThreshold : Int,
    val gasThreshold : Double,
    val scanFrequency : Int)