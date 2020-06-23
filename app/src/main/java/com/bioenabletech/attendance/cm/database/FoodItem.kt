package com.bioenabletech.attendance.cm.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "food_items", indices = [Index(value = ["name"], unique = true)])
class FoodItem(

    @ColumnInfo(name = "name")
    val itemName:String,

    @ColumnInfo(name = "price")
    val price:Int) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id : Int = 0
}