package com.example.usahaku.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class temppesprodukvm (application: Application) : AndroidViewModel(application){
    private val repository: temppesprodukrepos
    val alltempspes : LiveData<List<temppesproduk>>

    init {
        val tempspesDao = temppesprodukdb.getDatabase(application, viewModelScope).temppesprodukdao()
        repository = temppesprodukrepos(tempspesDao)
        alltempspes = repository.alltempspes
    }

    fun  delete(temppesproduk: temppesproduk) = viewModelScope.launch {
        repository.delete(temppesproduk)
    }
    fun insert(temppesproduk: temppesproduk) = viewModelScope.launch {
        repository.insert(temppesproduk)
    }
    fun deleteALLtemppesproduk() = viewModelScope.launch {
        repository.deleteALLtemppesproduk()
    }

}