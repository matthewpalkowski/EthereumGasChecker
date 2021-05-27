package com.mpcrypto.ethereumgaschecker.etherscanapi

import GasPriceReturn
import retrofit2.Call
import retrofit2.http.*

//TODO change below strings to constants
interface IEtherscanAPICalls {
    @GET(".")
    fun getGasPrice(
        @Query("module") module: String,
        @Query("action") action: String,
        @Query("apikey") key: String)
        : Call<GasPriceReturn>
}