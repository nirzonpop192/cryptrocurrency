package com.example.cointrack.repos

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.cointrack.models.CurrencyStorageDM
import com.example.cointrack.stroage.room.CurrencyDao
import com.example.cointrack.stroage.room.CurrencyDatabase
import com.example.cointrack.utils.subscribeOnBackground

class StorageRepository(application: Application) {



    private  var currencyDao : CurrencyDao
    private lateinit var allCurrency: LiveData<List<CurrencyStorageDM>>

    private val database = CurrencyDatabase.getInstance(application)


    init {
        currencyDao = database.currencyDao()

    }

    fun insert(currency: CurrencyStorageDM) {

        subscribeOnBackground {
            currencyDao.insert(currency)
        }
    }
}