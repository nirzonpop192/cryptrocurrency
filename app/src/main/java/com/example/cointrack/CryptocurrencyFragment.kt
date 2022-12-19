package com.example.cointrack

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.cointrack.viewmodels.CryptocurrencyViewModel

class CryptocurrencyFragment : Fragment() {
    private val cryptocurrencyViewModel : CryptocurrencyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cryptocurrencyViewModel.fetchData()
        cryptocurrencyViewModel.cryptoLiveData.observe(viewLifecycleOwner) {
            Log.d("CryptocurrencyFragment", it.data[0].name)
        }
        return inflater.inflate(R.layout.fragment_cryptocurrency, container, false)
    }
}