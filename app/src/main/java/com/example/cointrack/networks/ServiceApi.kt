package com.example.cointrack.networks

import com.example.cointrack.models.CryptoModel
import retrofit2.http.GET
import retrofit2.http.Url

interface ServiceApi{
    @GET
    suspend fun getCryptocurrency(@Url endUrl : String) : CryptoModel
}