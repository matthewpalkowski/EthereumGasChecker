package etherscanapi

import com.mpcrypto.ethereumgaschecker.constants.ApiKeys
import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import GasPriceReturn
import ValueObservable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Etherscan : ValueObservable() {

    private lateinit var etherscanRequest: IEtherscanAPICalls
    private lateinit var retrofitEtherscan: Retrofit

    fun queryGas(){
        retrofitEtherscan = Retrofit.Builder()
            .baseUrl(StringConstants.ETHERSCAN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        etherscanRequest = retrofitEtherscan.create(IEtherscanAPICalls::class.java)

        etherscanRequest.getGasPrice(
            StringConstants.ETHERSCAN_GAS_MODULE,
            StringConstants.ETHERSCAN_ACTION,
            ApiKeys.ETHERSCAN_API_KEY)
            .enqueue(object : Callback<GasPriceReturn>{
                override fun onResponse(call: Call<GasPriceReturn>, response: Response<GasPriceReturn>) {
                    if(response.body() == null) {
                        print(response.code()) //TODO FIGURE OUT WARNING MECHANISMS
                    }
                    else {
                        //sets value to current GasPrice and notifies observers of new value
                        value = response.body()!!.result
                        notifyObservers()
                    }
                }

                override fun onFailure(call: Call<GasPriceReturn>, t: Throwable) {
                    //TODO Catch missing internet connection and put warning message about internet connection
                    //TODO if not mia internet, put error message for unhandled exception
                }
            })
    }
}