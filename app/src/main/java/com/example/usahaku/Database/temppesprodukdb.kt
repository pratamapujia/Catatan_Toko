package com.example.usahaku.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = arrayOf(temppesproduk::class), version = 1, exportSchema = false)
public abstract class temppesprodukdb : RoomDatabase(){
    abstract fun temppesprodukdao(): temppesprodukDao


    private class  temppesprodukDataBaseCallBack (
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.temppesprodukdao())
                }
            }
        }
        suspend fun populateDatabase(tempdao:temppesprodukDao){
            tempdao.deleteALLtemppesproduk()


        }
    }
    companion object {
        @Volatile
        private var INSTANCE: temppesprodukdb? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): temppesprodukdb {
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,temppesprodukdb::class.java,
                    "tabel_temppesproduk")
                    .addCallback(temppesprodukDataBaseCallBack(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}