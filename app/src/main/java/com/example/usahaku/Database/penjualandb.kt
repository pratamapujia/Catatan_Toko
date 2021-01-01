package com.example.usahaku.Database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [penjualan::class], version = 1)
abstract class penjualandb : RoomDatabase() {

    abstract fun penjualandao(): Penjualandao
    companion object {
        private var instance: penjualandb? = null
        fun getInstance(context: Context): penjualandb? {
            if (instance == null) {
                synchronized(penjualandb::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        penjualandb::class.java, "penjualan_database"  )
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

    class PopulateDbAsyncTask(db: penjualandb?) : AsyncTask<Unit, Unit, Unit>() {
        private val penjualandao = db?.penjualandao()

        override fun doInBackground(vararg p0: Unit?) {
           // penjualandao?.insert(penjualan("Annisa", "Minyak", "28/05/2020",9))
        }
    }

}