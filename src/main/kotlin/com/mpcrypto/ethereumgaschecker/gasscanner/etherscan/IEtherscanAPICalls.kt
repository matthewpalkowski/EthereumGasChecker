package com.mpcrypto.ethereumgaschecker.gasscanner.etherscan

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import retrofit2.Call
import retrofit2.http.*

/**
 * Interface to conform the contract required by the Retrofit library to interact with the Etherscan.io API
 */
interface IEtherscanAPICalls {

    /**API contract per https://etherscan.io/apis#proxy -> eth_gasPrice
     *   https://api.etherscan.io/api?module=proxy&action=eth_gasPrice&apikey=YourApiKeyToken
     *
     * Sample return
     *   {"jsonrpc":"2.0","id":73,"result":"0x2540be400"}
     **/
    @GET("api")
    fun getGasPrice(
        @Query(StringConstants.ETHERSCAN_KEY_MODULE) module: String,
        @Query(StringConstants.ETHERSCAN_KEY_ACTION) action: String,
        @Query(StringConstants.ETHERSCAN_KEY_APIKEY) key: String)
        : Call<GasPriceReturn>

    /**API contract per https://etherscan.io/apis#proxy -> Get Gas Oracle
     *   https://api.etherscan.io/api?module=gastracker&action=gasoracle&apikey=YourApiKeyToken
     *
     * Sample return
     *   {"status":"1","message":"OK","result":{"LastBlock":"12614307","SafeGasPrice":"24","ProposeGasPrice":"31","FastGasPrice":"38"}}
     **/
    @GET("api")
    fun getGasOracle(
        @Query(StringConstants.ETHERSCAN_KEY_MODULE) module: String,
        @Query(StringConstants.ETHERSCAN_KEY_ACTION) action: String,
        @Query(StringConstants.ETHERSCAN_KEY_APIKEY) key: String)
        : Call<GasOracleReturn>
}