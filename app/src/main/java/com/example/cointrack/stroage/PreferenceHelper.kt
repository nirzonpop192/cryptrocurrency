package com.example.cointrack.stroage

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object PreferenceHelper {
    val TIMER = "TIMER"


    //fun defaultPreference(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context): SharedPreferences = context.getSharedPreferences("COIN", Context.MODE_PRIVATE)

   private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.timer
        get() = getInt(TIMER, 1)
        set(value) {
            if(value>0 && value<16){
                editMe {
                    it.putInt(TIMER, value)
                }
            }
        }



    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }

}