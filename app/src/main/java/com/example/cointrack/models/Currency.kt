package com.example.cointrack.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class Currency(

    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "usdPrice") val usdPrice: Double,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "entryDateTime") val entryDateTime: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
)

