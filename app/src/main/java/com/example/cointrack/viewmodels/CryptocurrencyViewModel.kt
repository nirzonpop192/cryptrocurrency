package com.example.cointrack.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cointrack.models.CryptoModel
import com.example.cointrack.models.CurrencyStorageDM
import com.example.cointrack.repos.CryptocurrencyRepository
import com.example.cointrack.repos.StorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CryptocurrencyViewModel() : ViewModel() {
    val repository = CryptocurrencyRepository()

   companion object {var cryptoLiveData : MutableLiveData<CryptoModel> = MutableLiveData()}

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

    fun insert(application: Application,currency: CurrencyStorageDM) {
        StorageRepository(application).insert(currency)
    }


}