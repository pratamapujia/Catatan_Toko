package com.example.usahaku.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class pelangganviewmodel(application: Application) : AndroidViewModel(application) {

    private var repository: pelangganrepositori = pelangganrepositori(application)
    private var allpelanggan: LiveData<List<pelanggan>> = repository.getAllpelanggan()
    fun insert(pelanggan: pelanggan) {
        repository.insert(pelanggan)
    }
    fun update(pelanggan: pelanggan) {
        repository.update(pelanggan)
    }
    fun delete(pelanggan: pelanggan) {
        repository.delete(pelanggan)
    }
    fun getAllpelanggan(): LiveData<List<pelanggan>> {
        return allpelanggan
    }
}