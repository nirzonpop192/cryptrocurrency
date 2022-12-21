package com.example.cointrack.stroage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cointrack.models.Currency
import com.example.cointrack.utils.subscribeOnBackground

@Database(entities = [Currency::class], version = 2)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        private var instance: CurrencyDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): CurrencyDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, CurrencyDatabase::class.java,
                    "currency_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
//                populateDatabase(instance!!)
            }
        }

        private fun populateDatabase(db: CurrencyDatabase) {
            val noteDao = db.currencyDao()
            subscribeOnBackground {
//                noteDao.insert(Note("title 1", "desc 1", 1))
//                noteDao.insert(Note("title 2", "desc 2", 2))
//                noteDao.insert(Note("title 3", "desc 3", 3))

            }
        }
    }



}
