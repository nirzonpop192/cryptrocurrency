package com.example.cointrack

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.cointrack.networks.NetworkManager
import com.example.cointrack.viewmodels.CryptocurrencyViewModel

class CryptocurrencyFragment : Fragment() {
    private val cryptocurrencyViewModel : CryptocurrencyViewModel by activityViewModels()

    var handler : Handler = Handler()
    var runnable : Runnable? = null
    var delay = 5*1000


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        cryptocurrencyViewModel.cryptoLiveData.observe(viewLifecycleOwner) {
            Log.d("CryptocurrencyFragment", it.data[0].name)
        }
        return inflater.inflate(R.layout.fragment_cryptocurrency, container, false)
    }

    override  fun onResume() {
//        handler.postDelayed(runnable!!, delay.toLong())
//        cryptocurrencyViewModel.cryptoLiveData.observe(viewLifecycleOwner) {
//            Log.d("CryptocurrencyFragment", it.data[0].name)
//        }.also { runnable = it }, delay.toLonng())

        handler.postDelayed(Runnable {

            handler.postDelayed(runnable!!, delay.toLong())
            context?.let {
                if(NetworkManager.isNetConnectionAvailable(it))
                    cryptocurrencyViewModel.fetchData()
            }
        }.also { runnable=it },delay.toLong())
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable!!)
    }
}