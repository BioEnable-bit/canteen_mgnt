package com.bioenabletech.attendance.cm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {
    @Insert
    fun Insert(transaction: List<Transaction>)

    @Query("select * from transactions")
    fun getAll(): LiveData<List<Transaction>>

    @Query("select id from transactions ORDER BY timestamp DESC  LIMIT 1")
    fun getTransactionId(): LiveData<Int>
}