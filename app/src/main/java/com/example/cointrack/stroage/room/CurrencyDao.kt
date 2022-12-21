package com.example.cointrack.stroage.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cointrack.models.CurrencyStorageDM

@Dao
interface CurrencyDao {

    @Insert
    fun insert(note: CurrencyStorageDM)

    @Update
    fun update(note: CurrencyStorageDM)

    @Delete
    fun delete(note: CurrencyStorageDM)

    @Query("delete from currency_table")
    fun deleteAllNotes()

    @Query("select * from currency_table order by name desc")
    fun getAllNotes(): LiveData<List<CurrencyStorageDM>>
}