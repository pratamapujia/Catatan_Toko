package com.example.usahaku.Database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


class pelangganrepositori(application: Application) {
    private var pelanggandao:Pelanggandao
    private var allpelanggan: LiveData<List<pelanggan>>
    init {
        val database: pelanggandb = pelanggandb.getInstance(
            application.applicationContext
        )!!
        pelanggandao = database.pelanggandao()
        allpelanggan = pelanggandao.getAllpelanggan()

    }
    fun insert(pelanggan: pelanggan) {
        val insertpelangganAsyncTask =
            InsertpelangganAsyncTask(pelanggandao).execute(pelanggan)
    }
    fun update(pelanggan: pelanggan) {
        val updatepelangganAsyncTask =
            UpdatepelangganAsyncTask(pelanggandao).execute(pelanggan)
    }
    fun delete(pelanggan: pelanggan) {
        val deletepelangganAsyncTask =
            DeletepelangganAsyncTask(pelanggandao).execute(pelanggan)
    }

    fun getAllpelanggan(): LiveData<List<pelanggan>> {
        return allpelanggan
    }

    companion object {
        private class InsertpelangganAsyncTask(pelanggandao: Pelanggandao) :
            AsyncTask<pelanggan, Unit, Unit>() {
            val pelanggandao = pelanggandao
            override fun doInBackground(vararg p0: pelanggan?) {
                pelanggandao.insert(p0[0]!!)
            }
        }
        private class UpdatepelangganAsyncTask(pelanggandao: Pelanggandao) :
            AsyncTask<pelanggan, Unit, Unit>() {
            val pelanggandao= pelanggandao

            override fun doInBackground(vararg p0: pelanggan?) {
                pelanggandao.update(p0[0]!!)
            }
        }
        private class DeletepelangganAsyncTask(pelanggandao: Pelanggandao) :
            AsyncTask<pelanggan, Unit, Unit>() {
            val pelanggandao = pelanggandao

            override fun doInBackground(vararg p0: pelanggan?) {
                pelanggandao.delete(p0[0]!!)
            }
        }
    }
}


