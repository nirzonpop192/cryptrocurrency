package com.example.cointrack.repos

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cointrack.models.Currency
import com.example.cointrack.stroage.room.CurrencyDao
import com.example.cointrack.stroage.room.CurrencyDatabase
import com.example.cointrack.utils.subscribeOnBackground

class StorageRepository(application: Application) {



    private  var currencyDao : CurrencyDao
    private lateinit var allCurrency: LiveData<List<Currency>>

    private val database = CurrencyDatabase.getInstance(application)


    init {
        currencyDao = database.currencyDao()

    }

    fun insert(currency: Currency) {

        subscribeOnBackground {
            currencyDao.insert(currency)
        }
    }

    fun getAllCurrency(): LiveData<List<Currency>> {
        allCurrency = currencyDao.getAllCurrency()
        return allCurrency
    }

}