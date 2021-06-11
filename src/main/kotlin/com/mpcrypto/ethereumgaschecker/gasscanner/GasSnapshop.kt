package com.mpcrypto.ethereumgaschecker.gasscanner

/**
 * Data class to simply interactions with ValuesList of GasHistory.
 */
data class GasSnapshot(
    val gasValue : Int,
    val timeStamp : Long
)
