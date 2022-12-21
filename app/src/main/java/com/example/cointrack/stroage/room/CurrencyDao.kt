package com.example.cointrack.stroage.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cointrack.models.Currency

@Dao
interface CurrencyDao {

    @Insert
    fun insert(note: Currency)

    @Update
    fun update(note: Currency)

    @Delete
    fun delete(note: Currency)

    @Query("delete from currency_table")
    fun deleteAllNotes()

    @Query("select * from currency_table order by name desc")
    fun getAllCurrency(): LiveData<List<Currency>>
}