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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CryptocurrencyViewModel : ViewModel() {

    private val repository = CryptocurrencyRepository()
    lateinit var allCryLiveData : LiveData<List<Currency>>
    var isDataBaseEmpty : MutableLiveData<Boolean> = MutableLiveData()
    var isLoading : MutableLiveData<Boolean> = MutableLiveData()


    companion object {

       var cryptoLiveData : MutableLiveData<CryptoModel> = MutableLiveData()
        private   val TAG:String= CryptocurrencyViewModel::class.java.name
   }


    fun fetchData() {
        //isLoading.value=true
        viewModelScope.launch {
            try {
                cryptoLiveData.value = repository.fetchCryptoData()
            }catch (e : Exception) {
                Log.d(TAG, e.localizedMessage)
            }
        }
    }

    fun insert(application: Application,currency: Currency) {
        StorageRepository(application).insert(currency)
    }

    fun getAllCurrency(application: Application): LiveData<List<Currency>> {
        allCryLiveData=StorageRepository(application).getAllCurrency()

        return allCryLiveData

    }

    fun isDBEmpty(application: Application) :Boolean{
             var  flag=false
        viewModelScope.launch(Dispatchers.Main) {
            try {
                flag=StorageRepository(application).getTotalNumber()==0

            }catch (e : Exception) {
                Log.d(TAG, e.localizedMessage)
            }
        }
        Log.e(TAG,"is db empty :"+flag)
        return flag
    }

}