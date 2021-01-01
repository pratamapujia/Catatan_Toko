package com.example.usahaku.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = arrayOf(tempproduk::class), version = 1, exportSchema = false)
public abstract class tempprodukdb : RoomDatabase(){
    abstract fun tempprodukdao(): tempprodukDao


    private class  tempprodukDataBaseCallBack (
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.tempprodukdao())
                }
            }
        }
        suspend fun populateDatabase(tempdao:tempprodukDao){
            tempdao.deleteALLtempproduk()


        }
    }
    companion object {
        @Volatile
        private var INSTANCE: tempprodukdb? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): tempprodukdb {
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,tempprodukdb::class.java,
                    "tabel_tempproduk")
                    .addCallback(tempprodukDataBaseCallBack(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}