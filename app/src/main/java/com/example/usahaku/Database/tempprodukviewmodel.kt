package com.example.usahaku.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class tempprodukViewModel (application: Application) : AndroidViewModel(application){
    private val repository: tempprodukRepository
    val alltemps : LiveData<List<tempproduk>>

    init {
        val tempsDao = tempprodukdb.getDatabase(application, viewModelScope).tempprodukdao()
        repository = tempprodukRepository(tempsDao)
        alltemps = repository.alltemps
    }

    fun  delete(tempproduk: tempproduk) = viewModelScope.launch {
        repository.delete(tempproduk)
    }
    fun insert(tempproduk: tempproduk) = viewModelScope.launch {
        repository.insert(tempproduk)
    }
    fun deleteALLtempproduk() = viewModelScope.launch {
        repository.deleteALLtempproduk()
    }

}