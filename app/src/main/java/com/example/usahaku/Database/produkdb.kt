package com.example.usahaku.Database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [produk::class], version = 1)
abstract class produkdb : RoomDatabase() {
    abstract fun Produkdao(): Produkdao
    companion object {
        private var instance: produkdb? = null
        fun getInstance(context: Context): produkdb? {
            if (instance == null) {
                synchronized(produkdb::class) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            produkdb::class.java, "produk_database"  )
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
            } } }
    class PopulateDbAsyncTask(db: produkdb?) : AsyncTask<Unit, Unit, Unit>() {
        private val Produkdao = db?.Produkdao()
        override fun doInBackground(vararg p0: Unit?) {
           // Produkdao?.insert(produk("Minyak", "Minyak mantab", 12000,15000,10,"KG"))
        }
    }

}