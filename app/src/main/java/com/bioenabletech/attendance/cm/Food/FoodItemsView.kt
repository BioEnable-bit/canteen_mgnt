package com.bioenabletech.attendance.cm.Food

interface FoodItemsView {

    fun showProgress()

    fun hideProgress()

    fun showMessage(message : String)

    fun finishActivity()
}