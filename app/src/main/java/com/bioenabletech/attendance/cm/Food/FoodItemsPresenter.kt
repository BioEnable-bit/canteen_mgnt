package com.bioenabletech.attendance.cm.Food

import android.util.Log
import com.bioenabletech.attendance.cm.app.IApp
import com.bioenabletech.attendance.cm.database.FoodItem
import java.lang.Exception

class FoodItemsPresenter {

    private lateinit var app : IApp
    private lateinit var foodItemsView: FoodItemsView

    constructor(app: IApp, foodItemsView: FoodItemsView) {
        this.app = app
        this.foodItemsView = foodItemsView
    }

    fun addfood(foodItem: List<FoodItem>){

        try {
            Thread(Runnable { ->
                app.database().foodItemDao().insert(foodItem)
                foodItemsView.showMessage("Food added succussfully")
                foodItemsView.finishActivity()
            }).start()
        }
        catch (e:Exception)
        {
            Log.e("TAG","Exception"+e)
        }

    }

    fun updatefood(id:Int,name:String,price:Int)
    {
        Thread(Runnable { ->
        app.database().foodItemDao().updateFood(id,name,price)
        foodItemsView.showMessage("Food updated succussfully")
        foodItemsView.finishActivity()
        }).start()

    }

    fun deleteFood(id:Int)
    {
        Thread(Runnable { ->
            app.database().foodItemDao().delete(id)
            foodItemsView.showMessage("Food deleted succussfully")
            foodItemsView.finishActivity()
        }).start()

    }
}