package com.example.usahaku.Database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


class produkrepositori(application: Application) {
    private var produkdao:Produkdao
    private var allproduk: LiveData<List<produk>>
    init {
        val database: produkdb = produkdb.getInstance(
            application.applicationContext
        )!!
        produkdao = database.Produkdao()
        allproduk = produkdao.getAllproduk()

    }
    fun insert(produk: produk) {
        val insertprodukAsyncTask = InsertprodukAsyncTask(produkdao).execute(produk)
    }
    fun update(produk: produk) {
        val updateprodukAsyncTask = UpdateprodukAsyncTask(produkdao).execute(produk)
    }
    fun delete(produk: produk) {
        val deleteprodukAsyncTask = DeleteprodukAsyncTask(produkdao).execute(produk)
    }
    fun getAllproduk(): LiveData<List<produk>> {
        return allproduk
    }
    companion object {
        private class InsertprodukAsyncTask(produkdao: Produkdao) : AsyncTask<produk, Unit, Unit>() {
            val produkdao = produkdao
            override fun doInBackground(vararg p0: produk?) {
                produkdao.insert(p0[0]!!)
            }
        }
        private class UpdateprodukAsyncTask(produkdao: Produkdao) : AsyncTask<produk, Unit, Unit>() {
            val produkdao= produkdao

            override fun doInBackground(vararg p0: produk?) {
                produkdao.update(p0[0]!!)
            }
        }
        private class DeleteprodukAsyncTask(produkdao: Produkdao) : AsyncTask<produk, Unit, Unit>() {
            val produkdao = produkdao

            override fun doInBackground(vararg p0: produk?) {
                produkdao.delete(p0[0]!!)
            }
        }
    }
}


