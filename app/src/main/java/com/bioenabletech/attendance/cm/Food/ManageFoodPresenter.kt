package com.bioenabletech.attendance.cm.Food

import com.bioenabletech.attendance.cm.app.IApp

class ManageFoodPresenter {

    private lateinit var app : IApp
    private lateinit var foodItemsView: FoodItemsView

    constructor(app: IApp, foodItemsView: FoodItemsView) {
        this.app = app
        this.foodItemsView = foodItemsView
    }

    fun deleteFood(id:Int)
    {
        Thread(Runnable { ->
            app.database().foodItemDao().delete(id)
            foodItemsView.showMessage("Food deleted succussfully")
         }).start()

    }

}