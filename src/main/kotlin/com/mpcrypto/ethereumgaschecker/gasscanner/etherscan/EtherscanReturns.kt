package com.mpcrypto.ethereumgaschecker.gasscanner.etherscan

//TODO See if this can be moved to be an inner class of the Etherscan class
/**
 * Class that maps onto the return values from the Etherscan.io GasPrice API endpoint for use in Gson conversion.
 */
data class GasPriceReturn(
    val jsonrpc: String,
    val id: Int,
    val result: Double)

/**
 * Class that maps onto the return values from the Etherscan.io GasOracle API for use in Gson conversion.
 */
data class GasOracleReturn(
    val status: Int,
    val message: String,
    val result: OracleResult)

/**
 * Class that maps onto the result class from the values returned by the Etherscan.io GasOracle API endpoint for use in
 * Gson conversion.
 */
data class OracleResult(
    val LastBlock: Int,
    val SafeGasPrice: Int,
    val ProposeGasPrice: Int,
    val FastGasPrice: Int)