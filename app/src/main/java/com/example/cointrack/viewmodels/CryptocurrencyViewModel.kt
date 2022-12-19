package com.example.cointrack.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cointrack.models.CryptoModel
import com.example.cointrack.repos.CryptocurrencyRepository
import kotlinx.coroutines.launch

class CryptocurrencyViewModel : ViewModel() {
    val repository = CryptocurrencyRepository()
    val cryptoLiveData : MutableLiveData<CryptoModel> = MutableLiveData()

    fun fetchData() {
        viewModelScope.launch {
            try {
                cryptoLiveData.value = repository.fetchCryptoData()
            }catch (e : Exception) {
                Log.d("CryptocurrencyViewModel", e.localizedMessage)
            }
        }
    }
}