package com.example.usahaku.Database

import androidx.lifecycle.LiveData


class temppesprodukrepos(private val tempppesrodukDao: temppesprodukDao){

    val alltempspes: LiveData<List<temppesproduk>> = tempppesrodukDao.alldatatemppesproduk()
    suspend fun insert(temppesproduk: temppesproduk){
        tempppesrodukDao.insert(temppesproduk)
    }

    suspend fun delete(temppesproduk: temppesproduk){

        tempppesrodukDao.delete(temppesproduk)
    }
    suspend fun deleteALLtemppesproduk(){
        tempppesrodukDao.deleteALLtemppesproduk()
    }

}