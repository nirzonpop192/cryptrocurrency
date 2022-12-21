package com.example.cointrack.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cointrack.models.CryptoModel
import com.example.cointrack.models.Currency
import com.example.cointrack.repos.CryptocurrencyRepository
import com.example.cointrack.repos.StorageRepository
import kotlinx.coroutines.launch

class CryptocurrencyViewModel : ViewModel() {
    private val repository = CryptocurrencyRepository()

   companion object {
       var cryptoLiveData : MutableLiveData<CryptoModel> = MutableLiveData()
   }
    lateinit var allCryLiveData : LiveData<List<Currency>>

    init {
//        allCryLiveData= StorageRepository(application).getAllCurrency()
    }
    fun fetchData() {
        viewModelScope.launch {
            try {
                cryptoLiveData.value = repository.fetchCryptoData()
            }catch (e : Exception) {
                Log.d("CryptocurrencyViewModel", e.localizedMessage)
            }
        }
    }


//    private val repository = NoteRepository(app)
//    private val allNotes = repository.getAllNotes()

    fun insert(application: Application,currency: Currency) {
        StorageRepository(application).insert(currency)
    }

    fun getAllCurrency(application: Application): LiveData<List<Currency>> {

//        viewModelScope.launch {
//            try {
//                allCryLiveData = StorageRepository(application).getAllCurrency()
//            }catch (e : Exception) {
//                Log.d("CryptocurrencyViewModel", e.localizedMessage)
//            }
//        }
        allCryLiveData=StorageRepository(application).getAllCurrency()

        return allCryLiveData
//        return StorageRepository(application).getAllCurrency()
    }
}