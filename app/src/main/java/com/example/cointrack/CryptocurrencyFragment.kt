package com.example.cointrack

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.fragment.app.activityViewModels
import com.example.cointrack.networks.NetworkManager
import com.example.cointrack.service.ServerJobService
import com.example.cointrack.viewmodels.CryptocurrencyViewModel

class CryptocurrencyFragment : Fragment() {
     val cryptocurrencyViewModel : CryptocurrencyViewModel by activityViewModels()

    private lateinit var jobScheduler:JobScheduler
    companion object {
        const val JOB_ID:Int =123
         val TAG:String= CryptocurrencyFragment.javaClass.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val  componentName =  ComponentName(requireContext(), ServerJobService::class.java)

        val  info: JobInfo = JobInfo.Builder(JOB_ID,componentName)
            .setRequiresCharging(true)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPersisted(true)
            .setPeriodic(1*1000)
            .build()

        jobScheduler= requireContext().getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        val resultCode=jobScheduler.schedule(info)

        if (resultCode==JobScheduler.RESULT_SUCCESS) Log.e(TAG,"job scheduler success")

        else if(resultCode==JobScheduler.RESULT_FAILURE) Log.e(TAG,"job scheduler failed")

        else Log.e(TAG,"job scheduler")

        cryptocurrencyViewModel.cryptoLiveData.observe(viewLifecycleOwner) {
            Log.d("CryptocurrencyFragment", it.data[0].name)
        }
        return inflater.inflate(R.layout.fragment_cryptocurrency, container, false)
    }

    override  fun onResume() {
        super.onResume()
//        handler.postDelayed(runnable!!, delay.toLong())
//        cryptocurrencyViewModel.cryptoLiveData.observe(viewLifecycleOwner) {
//            Log.d("CryptocurrencyFragment", it.data[0].name)
//        }.also { runnable = it }, delay.toLonng())


            val  componentName =  ComponentName(requireContext(), ServerJobService::class.java)

               val  info: JobInfo = JobInfo.Builder(JOB_ID,componentName)
                    .setRequiresCharging(true)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                    .setPersisted(true)
                    .setPeriodic(1*1000)
                    .build()

                 jobScheduler= requireContext().getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

              val resultCode=jobScheduler.schedule(info)

               if (resultCode==JobScheduler.RESULT_SUCCESS) Log.e(TAG,"job scheduler success")

               else if(resultCode==JobScheduler.RESULT_FAILURE) Log.e(TAG,"job scheduler failed")

               else Log.e(TAG,"job scheduler")



    }

    override fun onPause() {
        super.onPause()
        jobScheduler.cancel(JOB_ID)
    }
}