package com.bioenabletech.attendance.cm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodItemDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(foodItems: List<FoodItem>)

    @Query("select * from food_items")
    fun getAll() : LiveData<List<FoodItem>>

    @Query("update food_items set name= :name, price= :price where id= :id")
    fun updateFood(id:Int,name:String,price:Int)

    @Query("delete from food_items where id = :id")
    fun delete(id: Int?)

}