package com.example.cointrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cointrack.stroage.PreferenceHelper
import com.example.cointrack.stroage.PreferenceHelper.timer
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TimerSetterFragment : Fragment() {

    lateinit var seekBar: SeekBar
    lateinit var tv_progress: TextView
    lateinit var btnSetTimer: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view= inflater.inflate(R.layout.fragment_timer_setter, container, false)
        seekBar=view.findViewById<SeekBar>(R.id.seekBar)
        tv_progress=view.findViewById<TextView>(R.id.tv_progress)
        btnSetTimer=view.findViewById<Button>(R.id.btn_set_timer)

        setListener()
        val prefs = PreferenceHelper.customPreference(requireContext())

        seekBar.progress=prefs.timer
        tv_progress.text=prefs.timer.toString()
        return view;
    }

    fun setListener(){

        seekBar.setOnSeekBarChangeListener(
            object : OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    tv_progress.text=progress.toString()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                }
            }
        )

        btnSetTimer.setOnClickListener {
            val prefs = PreferenceHelper.customPreference(requireContext())
            val value=tv_progress.text.toString().toInt()
            if(value<16){
                prefs.timer=tv_progress.text.toString().toInt()
                findNavController().navigate(R.id.action_timerSetterFragment_to_cryptocurrencyFragment)
            }else
                Toast.makeText(context,"Invalid Entry",Toast.LENGTH_SHORT).show()
        }

    }


}