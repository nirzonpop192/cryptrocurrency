package com.example.cointrack.networks

import com.example.cointrack.models.CryptoModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

const val cryptocurrency_api_key = "fae43423-c927-4123-b5e7-e4a1ee701c47"
const val base_url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/"

val retrofit = Retrofit.Builder()
    .baseUrl(base_url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface CryptocurrencyServiceApi{
    @GET
    suspend fun getCryptocurrency(@Url endUrl : String) : CryptoModel
}

object NetworkService {
    val cryptocurrencyServiceApi : CryptocurrencyServiceApi by lazy {
        retrofit.create(CryptocurrencyServiceApi::class.java)
    }
}