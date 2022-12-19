package com.example.cointrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.cointrack.viewmodels.CryptocurrencyViewModel

class MainActivity : AppCompatActivity() {
    private val cryptocurrencyViewModel : CryptocurrencyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}