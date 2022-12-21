package com.example.cointrack.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cointrack.models.CryptoModel
import com.example.cointrack.repos.CryptocurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CryptocurrencyViewModel : ViewModel() {
    val repository = CryptocurrencyRepository()
   companion object {var cryptoLiveData : MutableLiveData<CryptoModel> = MutableLiveData()}

    fun fetchData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                cryptoLiveData.value = repository.fetchCryptoData()
            }catch (e : Exception) {
                Log.d("CryptocurrencyViewModel", e.localizedMessage)
            }
        }
    }
}