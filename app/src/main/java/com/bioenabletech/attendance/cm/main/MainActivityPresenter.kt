package com.bioenabletech.attendance.cm.main

import com.bioenabletech.attendance.cm.Food.FoodItemsView
import com.bioenabletech.attendance.cm.app.IApp
import com.bioenabletech.attendance.cm.database.Sale
import com.bioenabletech.attendance.cm.database.Transaction

class MainActivityPresenter {

    private lateinit var app : IApp
    private lateinit var mainActivityView: MainActivityView

    constructor(app: IApp, mainActivityView: MainActivityView) {
        this.app = app
        this.mainActivityView = mainActivityView
    }

    fun addTransaction(transaction : List<Transaction>)
    {
        Thread(Runnable { ->
            app.database().transactionDao().Insert(transaction)
            mainActivityView.showMessage("Transaction added successfully")

        }).start()
    }

    fun addSale(sale: List<Sale>)
    {
        Thread(Runnable { ->
            app.database().saleDao().Insert(sale)
            mainActivityView.showMessage("Sales added successfully")

        }).start()
    }
}