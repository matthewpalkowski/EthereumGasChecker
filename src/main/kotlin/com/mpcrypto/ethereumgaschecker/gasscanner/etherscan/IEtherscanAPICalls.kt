package com.mpcrypto.ethereumgaschecker.gasscanner.etherscan

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import retrofit2.Call
import retrofit2.http.*

/**
 * Interface to conform the contract required by the Retrofit library to interact with the Etherscan.io API
 */
interface IEtherscanAPICalls {
    @GET("api")
    fun getGasPrice(
        @Query(StringConstants.ETHERSCAN_KEY_MODULE) module: String,
        @Query(StringConstants.ETHERSCAN_KEY_ACTION) action: String,
        @Query(StringConstants.ETHERSCAN_KEY_APIKEY) key: String)
        : Call<GasPriceReturn>
}