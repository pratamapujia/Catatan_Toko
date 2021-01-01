package com.example.usahaku.Database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


class penjualanrepositori(application: Application) {

    private var penjualandao:Penjualandao

    private var allpenjualan: LiveData<List<penjualan>>

    init {
        val database: penjualandb = penjualandb.getInstance(
            application.applicationContext
        )!!
        penjualandao = database.penjualandao()
        allpenjualan = penjualandao.getAllpenjualan()

    }
    fun insert(penjualan: penjualan) {
        val insertpenjualanAsyncTask = InsertpenjualanAsyncTask(penjualandao).execute(penjualan)
    }
    fun update(penjualan: penjualan) {
        val updatepenjualanAsyncTask = UpdatepenjualanAsyncTask(penjualandao).execute(penjualan)
    }
    fun delete(penjualan: penjualan) {
        val deletepenjualanAsyncTask = DeletepenjualanAsyncTask(penjualandao).execute(penjualan)
    }

    fun getAllpenjualan(): LiveData<List<penjualan>> {
        return allpenjualan
    }

    companion object {
        private class InsertpenjualanAsyncTask(penjualandao: Penjualandao) : AsyncTask<penjualan, Unit, Unit>() {
            val penjualandao = penjualandao
            override fun doInBackground(vararg p0: penjualan?) {
                penjualandao.insert(p0[0]!!)
            }
        }
        private class UpdatepenjualanAsyncTask(penjualandao: Penjualandao) : AsyncTask<penjualan, Unit, Unit>() {
            val penjualandao= penjualandao

            override fun doInBackground(vararg p0: penjualan?) {
                penjualandao.update(p0[0]!!)
            }
        }
        private class DeletepenjualanAsyncTask(penjualandao: Penjualandao) : AsyncTask<penjualan, Unit, Unit>() {
            val penjualandao = penjualandao

            override fun doInBackground(vararg p0: penjualan?) {
                penjualandao.delete(p0[0]!!)
            }
        }


    }


}