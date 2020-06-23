package com.bioenabletech.attendance.cm.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
class Transaction(

    @ColumnInfo(name = "uid")
    var uid: String,

    @ColumnInfo(name = "amount")
    var amount:Int,

    @ColumnInfo(name = "timestamp")

    var timestamp: Long
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int = 0


}