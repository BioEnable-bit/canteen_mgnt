package com.bioenabletech.attendance.cm.app

import com.bioenabletech.attendance.cm.database.Db

interface IApp {
    fun database() : Db
}