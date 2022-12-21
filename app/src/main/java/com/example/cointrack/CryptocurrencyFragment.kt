package com.example.cointrack

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cointrack.adapter.CurrencyAdapter
import com.example.cointrack.models.Currency
import com.example.cointrack.networks.NetworkManager
import com.example.cointrack.service.ServerJobService
import com.example.cointrack.utils.getCurrentDateTime
import com.example.cointrack.utils.toString
import com.example.cointrack.viewmodels.CryptocurrencyViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CryptocurrencyFragment : Fragment() {

     private val viewModel : CryptocurrencyViewModel by activityViewModels()

    private lateinit var jobScheduler:JobScheduler

    companion object {
        const val JOB_ID:Int =123
        val TAG:String= CryptocurrencyFragment.javaClass.name
    }

    lateinit var recyclerview:RecyclerView
    lateinit var progressBarView :ProgressBar
    lateinit var fabtnTimer :FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view: View = inflater.inflate(R.layout.fragment_cryptocurrency, container, false)

        // getting the recyclerview by its id
         recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview)
         progressBarView = view.findViewById<ProgressBar>(R.id.simpleProgressBar)
        fabtnTimer=view.findViewById<FloatingActionButton>(R.id.fabtn_timer)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)


        // This will pass the ArrayList to our Adapter
        val adapter = CurrencyAdapter()

        recyclerview.setHasFixedSize(true)
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        viewModel.isDBEmpty(requireActivity().application)
        viewModel.isDataBaseEmpty.observe(viewLifecycleOwner, Observer { it->
            if (it) {
                if (NetworkManager.isNetConnectionAvailable(requireContext())){
                    viewModel.isLoading.value=true
                    viewModel.fetchData()

                }
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { flag->
            if(flag)
                progressBarView.visibility=View.VISIBLE
            else
                progressBarView.visibility=View.INVISIBLE
        })

            viewModel.getAllCurrency(requireActivity().application).observe(viewLifecycleOwner, Observer { use->

                Log.e(TAG," use size ${use?.size}");
                      viewModel.isLoading.value=false
                adapter.setCurrency(use)

            })


        return view
    }

    override  fun onResume() {
        super.onResume()

        stratJobScheduler()
        fabtnTimer.setOnClickListener {
            findNavController().navigate(R.id.action_cryptocurrencyFragment_to_timerSetterFragment)
        }

        CryptocurrencyViewModel.cryptoLiveData.observe(viewLifecycleOwner) {

            for (item in it.data){
                Log.e(TAG, item.name)
                val date = getCurrentDateTime()
                val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")
                val currency: Currency=  Currency(item.name, item.quote.uSD.price,item.symbol,dateInString)
                viewModel.insert(application = requireActivity().application,currency)

            }
            viewModel.isLoading.value=false

        }
    }
    fun  stratJobScheduler(){
        val  componentName =  ComponentName(requireContext(), ServerJobService::class.java)

        val  info: JobInfo = JobInfo.Builder(JOB_ID,componentName)
            .setMinimumLatency(1)
            .setRequiresCharging(false)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPersisted(true)
            //.setPeriodic(10*1000)
            .setOverrideDeadline(1*60*1000)
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