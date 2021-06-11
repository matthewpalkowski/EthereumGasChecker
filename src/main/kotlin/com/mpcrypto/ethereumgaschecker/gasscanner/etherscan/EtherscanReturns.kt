package com.mpcrypto.ethereumgaschecker.gasscanner.etherscan

//TODO See if this can be moved to be an inner class of the Etherscan class
/**
 * Class that maps onto the return values from the Etherscan.io API for use in Gson conversion.
 */
data class GasPriceReturn(
    val jsonrpc: String,
    val id: Int,
    val result: Double)

data class GasOracleReturn(
    val status: Int,
    val message: String,
    val result: OracleResult)

data class OracleResult(
    val LastBlock: Int,
    val SafeGasPrice: Int,
    val ProposeGasPrice: Int,
    val FastGasPrice: Int)