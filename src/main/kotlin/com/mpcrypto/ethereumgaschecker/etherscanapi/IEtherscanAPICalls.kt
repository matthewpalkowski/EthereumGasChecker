package com.mpcrypto.ethereumgaschecker.etherscanapi

import GasPriceReturn
import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import retrofit2.Call
import retrofit2.http.*

interface IEtherscanAPICalls {
    @GET(".") //TODO check if the "." can get removed
    fun getGasPrice(
        @Query(StringConstants.ETHERSCAN_KEY_MODULE) module: String,
        @Query(StringConstants.ETHERSCAN_KEY_ACTION) action: String,
        @Query(StringConstants.ETHERSCAN_KEY_APIKEY) key: String)
        : Call<GasPriceReturn>
}