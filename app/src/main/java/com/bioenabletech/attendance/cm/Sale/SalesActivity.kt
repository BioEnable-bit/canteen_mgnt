package com.bioenabletech.attendance.cm.Sale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bioenabletech.attendance.cm.R
import com.bioenabletech.attendance.cm.Transaction.TransactionsAdapter
import com.bioenabletech.attendance.cm.app.IApp
import com.bioenabletech.attendance.cm.database.Sale
import com.bioenabletech.attendance.cm.database.Transaction

class SalesActivity : AppCompatActivity() {

    private lateinit var app : IApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = applicationContext as IApp
        setContentView(R.layout.activity_sales)

        val rv = findViewById<RecyclerView>(R.id.rv_sales)
        rv.layoutManager = LinearLayoutManager(this)
        val adapter = SaleAdapter()
        rv.adapter = adapter

        app.database().saleDao().getAll().observe(this,object : Observer<List<Sale>>{
            override fun onChanged(t: List<Sale>) {
                adapter.setSale(t)
            }
        })


        val actionBar : ActionBar? = supportActionBar
        actionBar!!.title = "Sale history"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){

            android.R.id.home->
            {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
