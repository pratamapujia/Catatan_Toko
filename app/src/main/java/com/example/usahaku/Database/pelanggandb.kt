package com.example.usahaku.Database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [pelanggan::class], version = 1)
abstract class pelanggandb : RoomDatabase() {

    abstract fun pelanggandao(): Pelanggandao
    companion object {
        private var instance: pelanggandb? = null
        fun getInstance(context: Context): pelanggandb? {
            if (instance == null) {
                synchronized(pelanggandb::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        pelanggandb::class.java, "pelanggan_database"  )
                        .fallbackToDestructiveMigration() .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }
        fun destroyInstance() {
            instance = null
        }
        private val roomCallback = object :
            RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }
    }

    class PopulateDbAsyncTask(db: pelanggandb?) : AsyncTask<Unit, Unit, Unit>() {
        private val Pelanggandao = db?.pelanggandao()

        override fun doInBackground(vararg p0: Unit?) {
           //Pelanggandao?.insert(pelanggan("Minyak", "Minyak mantab", "Taman Pinang Indah","08226582992"))
        }
    }

}