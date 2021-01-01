package com.example.usahaku.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class produkviewmodel(application: Application) : AndroidViewModel(application) {

    private var repository: produkrepositori = produkrepositori(application)
    private var allproduk: LiveData<List<produk>> = repository.getAllproduk()
    fun insert(produk: produk) {
        repository.insert(produk)
    }
    fun update(produk: produk) {
        repository.update(produk)
    }
    fun delete(produk: produk) {
        repository.delete(produk)
    }
    fun getAllproduk(): LiveData<List<produk>> {
        return allproduk
    }
}