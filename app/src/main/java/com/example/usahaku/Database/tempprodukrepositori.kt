package com.example.usahaku.Database

import androidx.lifecycle.LiveData


class tempprodukRepository(private val tempprodukDao: tempprodukDao){

    val alltemps: LiveData<List<tempproduk>> = tempprodukDao.alldatatempproduk()
    //val sumhartot: LiveData<List<tempproduk>> = tempprodukDao.getsum()
    suspend fun insert(tempproduk: tempproduk){
        tempprodukDao.insert(tempproduk)
    }

    suspend fun delete(tempproduk: tempproduk){

        tempprodukDao.delete(tempproduk)
    }
    suspend fun deleteALLtempproduk(){
        tempprodukDao.deleteALLtempproduk()
    }

}