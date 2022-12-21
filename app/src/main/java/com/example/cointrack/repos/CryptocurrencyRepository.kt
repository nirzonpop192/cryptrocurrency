package com.example.cointrack.repos

import com.example.cointrack.models.CryptoModel
import com.example.cointrack.networks.NetworkService

class CryptocurrencyRepository {


    suspend fun fetchCryptoData(): CryptoModel {
        val endUrl = "listings/latest"
        return NetworkService.serviceApi
            .getCryptocurrency(endUrl)
    }


}