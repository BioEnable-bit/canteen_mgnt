package com.bioenabletech.attendance.cm.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
class Sale (

    @ColumnInfo(name = "transaction_id")
      var transactionId:Int,

    @ColumnInfo(name = "item_id")
      var itemId:Int,

    @ColumnInfo(name = "price")
      var price:Int,

    @ColumnInfo(name = "count")
      var count:Int,

    @ColumnInfo(name = "timestamp")
      var timestamp:Long) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
      var id : Int = 0
}