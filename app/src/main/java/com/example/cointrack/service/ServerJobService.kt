package com.example.cointrack.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Handler
import android.util.Log
import com.example.cointrack.CryptocurrencyFragment
import com.example.cointrack.networks.NetworkManager
import com.example.cointrack.repos.CryptocurrencyRepository
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
        const val delay = 1*60*1000
    }
    override fun onStartJob(params: JobParameters?): Boolean {


        Log.e(TAG,"onStartJob")

        handler.postDelayed(Runnable {

            handler.postDelayed(runnable!!, delay.toLong())

                if(NetworkManager.isNetConnectionAvailable(this))
                  scope.launch {
                      withContext(Dispatchers.Main){
                          CryptocurrencyViewModel.cryptoLiveData.value=repository.fetchCryptoData() }

                  } //   CryptocurrencyFragment().cryptocurrencyViewModel.fetchData()

        }.also { runnable=it },delay.toLong())
//        handler.post( Runnable {
//            if(NetworkManager.isNetConnectionAvailable(this))
//                CryptocurrencyFragment().cryptocurrencyViewModel.fetchData()
//
//        })
//        handler.postDelayed()

//        Thread(Runnable {
//            for (i in 0..9) {
//                Log.e(TAG, "run: $i")
//                if (jobCancelled) {
//                    return@Runnable
//                }
//                try {
//                    Thread.sleep(1000)
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                }
//            }
//            Log.e(TAG, "Job finished")
//           // jobFinished(params, false)
//        }).start()

        return  true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.e(TAG,"onStopJob")
//        handler.removeCallbacks(runnable!!)
        jobCancelled=true
        return true
    }
}