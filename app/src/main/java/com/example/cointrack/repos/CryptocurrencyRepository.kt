package com.example.cointrack.repos

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.cointrack.models.CryptoModel
import com.example.cointrack.models.CurrencyStorageDM
import com.example.cointrack.networks.NetworkService
import com.example.cointrack.stroage.room.CurrencyDao
import com.example.cointrack.stroage.room.CurrencyDatabase
import com.example.cointrack.utils.subscribeOnBackground

class CryptocurrencyRepository {


    suspend fun fetchCryptoData(): CryptoModel {
        val endUrl = "listings/latest"
        return NetworkService.serviceApi
            .getCryptocurrency(endUrl)
    }


}