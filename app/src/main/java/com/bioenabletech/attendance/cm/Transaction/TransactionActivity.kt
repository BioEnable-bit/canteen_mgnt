package com.bioenabletech.attendance.cm.Transaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bioenabletech.attendance.cm.Food.ManageFoodAdapter
import com.bioenabletech.attendance.cm.R
import com.bioenabletech.attendance.cm.app.IApp
import com.bioenabletech.attendance.cm.database.FoodItem
import com.bioenabletech.attendance.cm.database.Transaction

class TransactionActivity : AppCompatActivity() {

    private lateinit var app : IApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = applicationContext as IApp
        setContentView(R.layout.activity_transaction)



        val rv = findViewById<RecyclerView>(R.id.rv_transaction)
        rv.layoutManager = LinearLayoutManager(this)
        val adapter = TransactionsAdapter()
        rv.adapter = adapter
        app.database().transactionDao().getAll().observe(this, object : Observer<List<Transaction>> {

            override fun onChanged(t: List<Transaction>) {
                adapter.setTrasaction(t)
            }
        })

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.title = "Transaction history"
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
