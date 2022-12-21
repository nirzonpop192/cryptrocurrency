package com.example.cointrack.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Handler
import android.util.Log
import com.example.cointrack.CryptocurrencyFragment
import com.example.cointrack.networks.NetworkManager
import com.example.cointrack.repos.CryptocurrencyRepository
import com.example.cointrack.stroage.PreferenceHelper.customPreference
import com.example.cointrack.stroage.PreferenceHelper.timer
import com.example.cointrack.viewmodels.CryptocurrencyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ServerJobService: JobService() {
    var handler : Handler = Handler()
    var runnable : Runnable? = null
    var  jobCancelled:Boolean=false

    private val scope = CoroutineScope(Dispatchers.IO )

    val repository = CryptocurrencyRepository()
     companion object {
        val TAG:String = ServerJobService.javaClass.name
         var delay = 0
         const val miliSecon=60*1000
    }
    override fun onStartJob(params: JobParameters?): Boolean {


        Log.e(TAG,"onStartJob")
        val prefs = customPreference(this)

        val timer=+prefs.timer
        delay=timer*miliSecon

        Log.e(TAG, "in Job  timer $timer")
        Log.e(TAG, "in Job  delay $delay")

        handler.postDelayed(Runnable {

            handler.postDelayed(runnable!!, delay.toLong())

                if(NetworkManager.isNetConnectionAvailable(this))
                  scope.launch {
                      withContext(Dispatchers.Main){
                          CryptocurrencyViewModel.cryptoLiveData.value=repository.fetchCryptoData() }

                  } //   CryptocurrencyFragment().cryptocurrencyViewModel.fetchData()

        }.also { runnable=it },delay.toLong())


        return  true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.e(TAG,"onStopJob")
//        handler.removeCallbacks(runnable!!)
        jobCancelled=true
        return true
    }
}