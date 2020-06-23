package com.bioenabletech.attendance.cm.app

import android.app.Application
import com.bioenabletech.attendance.cm.database.Db

class App : Application(), IApp {

    private lateinit var db : Db

    override fun database(): Db {
        if(!::db.isInitialized) db = Db.getInstance(this)
        return db
    }
}