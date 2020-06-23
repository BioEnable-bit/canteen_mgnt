package com.bioenabletech.attendance.cm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SaleDao {
    @Insert
    fun Insert(sale: List<Sale>)

    @Query("select * from sales")
    fun getAll(): LiveData<List<Sale>>

}