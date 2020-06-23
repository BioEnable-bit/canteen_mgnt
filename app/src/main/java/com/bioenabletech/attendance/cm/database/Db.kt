package com.bioenabletech.attendance.cm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FoodItem::class,Transaction::class,Sale::class], version = 1, exportSchema = false)
abstract class Db : RoomDatabase() {

    companion object {

        @Volatile
        private lateinit var db : Db

        fun getInstance(ctx : Context): Db{
            synchronized(Db::class.java){
                return if(::db.isInitialized){
                    db
                } else {
                    db = Room.databaseBuilder(ctx,Db::class.java, "canteen_db")
                        .build()
                    db
                }
            }
        }
    }

    abstract fun foodItemDao() : FoodItemDao

    abstract fun transactionDao() : TransactionDao

    abstract fun saleDao() : SaleDao
}