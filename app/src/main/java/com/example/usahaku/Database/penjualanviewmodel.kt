package com.example.usahaku.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class penjualanviewmodel(application: Application) : AndroidViewModel(application) {

    private var repository: penjualanrepositori = penjualanrepositori(application)
    private var allpenjualan: LiveData<List<penjualan>> = repository.getAllpenjualan()
    fun insert(penjualan: penjualan) {
        repository.insert(penjualan)
    }
    fun update(penjualan: penjualan) {
        repository.update(penjualan)
    }
    fun delete(penjualan: penjualan) {
        repository.delete(penjualan)
    }
    fun getAllpenjualan(): LiveData<List<penjualan>> {
        return allpenjualan
    }
}