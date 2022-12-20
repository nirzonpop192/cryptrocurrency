package com.example.cointrack.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Handler
import android.util.Log

class ServerJobService: JobService() {
    var handler : Handler = Handler()
    var runnable : Runnable? = null
    var  jobCancelled:Boolean=false
     companion object {
        val TAG:String = ServerJobService.javaClass.name
        const val delay = 1*60*1000
    }
    override fun onStartJob(params: JobParameters?): Boolean {


        Log.e(TAG,"onStartJob")

//        handler.postDelayed(Runnable {
//
//            handler.postDelayed(runnable!!, delay.toLong())
//            context?.let {
//                if(NetworkManager.isNetConnectionAvailable(it))
//                    CryptocurrencyFragment().cryptocurrencyViewModel.fetchData()
//            }
//        }.also { runnable=it },delay.toLong())
//        handler.post( Runnable {
//            if(NetworkManager.isNetConnectionAvailable(this))
//                CryptocurrencyFragment().cryptocurrencyViewModel.fetchData()
//
//        })

        Thread(Runnable {
            for (i in 0..9) {
                Log.e(TAG, "run: $i")
                if (jobCancelled) {
                    return@Runnable
                }
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            Log.d(TAG, "Job finished")
            jobFinished(params, false)
        }).start()

        return  true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.e(TAG,"onStopJob")
//        handler.removeCallbacks(runnable!!)
        jobCancelled=true
        return true
    }
}