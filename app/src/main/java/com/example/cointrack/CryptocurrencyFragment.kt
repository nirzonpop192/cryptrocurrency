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
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cointrack.adapter.CurrencyAdapter
import com.example.cointrack.models.Currency
import com.example.cointrack.service.ServerJobService
import com.example.cointrack.utils.getCurrentDateTime
import com.example.cointrack.utils.toString
import com.example.cointrack.viewmodels.CryptocurrencyViewModel

class CryptocurrencyFragment : Fragment() {
//     private val viewModel : CryptocurrencyViewModel by activityViewModels()
     private val viewModel : CryptocurrencyViewModel by activityViewModels()

    private lateinit var jobScheduler:JobScheduler
    companion object {
        const val JOB_ID:Int =123
         val TAG:String= CryptocurrencyFragment.javaClass.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


         val view :View= inflater.inflate(R.layout.fragment_cryptocurrency, container, false)

        // getting the recyclerview by its id
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
//        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view


        // This will pass the ArrayList to our Adapter
        val adapter = CurrencyAdapter()

        recyclerview.setHasFixedSize(true)
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
//        if(NetworkManager.isNetConnectionAvailable(this))
//        viewModel.fetchData()

//        viewModel.getAllCurrency(requireActivity().application).observe()
//        viewModel.allCryLiveData.observe(viewLifecycleOwner) {
//            Log.e(TAG," size ${it.size}");
////            Log.d("CryptocurrencyFragment", it.data[0].name)
//        }

//        viewModel.allCryLiveData.observe(viewLifecycleOwner, object : Observer<List<Currency?>?> {
//            override fun onChanged(@Nullable notes: List<Currency?>?) {
//                if (notes!=null)
//                adapter.setCurrency(notes as List<Currency>)
//                Log.e(TAG," size ${notes?.size}");
//            }
//        })

//        viewModel.allCryLiveData.observe(viewLifecycleOwner) { profiles ->
//            if (profiles == null) return@observe
//
//
//            Log.e(TAG," profiles size ${profiles?.size}");
//            // you now have access to profiles, can even save them to the side and stuff
//            adapter.setCurrency(profiles as List<Currency>)
//        }
viewModel.getAllCurrency(requireActivity().application).observe(viewLifecycleOwner,
Observer { use->  Log.e(TAG," use size ${use?.size}");
    adapter.setCurrency(use)

})
//        viewModel.allCryLiveData.observe(viewLifecycleOwner, Observer {
//                user -> adapter.setCurrency(user)
//
//        })
//        viewModel.allCryLiveData.observe(viewLifecycleOwner, onChanged =  )
        return view
    }

    override  fun onResume() {
        super.onResume()

        stratJobscheduler()

        CryptocurrencyViewModel.cryptoLiveData.observe(viewLifecycleOwner) {

            for (item in it.data){
                Log.e(TAG, item.name)
                val date = getCurrentDateTime()
                val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")
                val currency: Currency=  Currency(item.name, item.quote.uSD.price,item.symbol,dateInString)
                viewModel.insert(application = requireActivity().application,currency)

            }


        }
    }
    fun  stratJobscheduler(){
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