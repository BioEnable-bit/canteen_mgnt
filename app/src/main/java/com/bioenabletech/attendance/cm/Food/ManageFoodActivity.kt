package com.bioenabletech.attendance.cm.Food

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bioenabletech.attendance.cm.R
import com.bioenabletech.attendance.cm.Sale.SalesActivity
import com.bioenabletech.attendance.cm.Transaction.TransactionActivity
import com.bioenabletech.attendance.cm.app.IApp
import com.bioenabletech.attendance.cm.database.FoodItem
import com.bioenabletech.attendance.cm.main.MainActivity
import kotlinx.coroutines.Dispatchers.Main

class ManageFoodActivity : AppCompatActivity(),ManageFoodAdapter.onClick,FoodItemsView{
    private lateinit var app : IApp
    private lateinit var presenter: ManageFoodPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = applicationContext as IApp
        presenter = ManageFoodPresenter(app, this)

        setContentView(R.layout.activity_manage_food)

        val actionBar = supportActionBar
        actionBar!!.title = "Manage food"
        actionBar!!.subtitle = "Canteen Management"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val rv = findViewById<RecyclerView>(R.id.rv_manage_food_items)
        rv.layoutManager = LinearLayoutManager(this)
        val adapter = ManageFoodAdapter(this,this)
        rv.adapter = adapter
        app.database().foodItemDao().getAll().observe(this, object : Observer<List<FoodItem>> {

            override fun onChanged(t: List<FoodItem>) {

                adapter.setFoodItems(t)

            }
        })

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.food_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_add_food->
            {
                val intent = Intent(this,
                    AddFoodActivity::class.java)
                startActivity(intent)
            }

            R.id.action_transaction_history->
            {
                val intent = Intent(this,
                    TransactionActivity::class.java)
                startActivity(intent)
            }
            R.id.action_sales_history->
            {
                val intent = Intent(this,
                    SalesActivity::class.java)
                startActivity(intent)
            }

            android.R.id.home->
            {
                onBackPressed()
            }
        }


        return super.onOptionsItemSelected(item)
    }
    override fun onDelete(id: Int, name: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete item")
        builder.setMessage( "Item "+name+" will be deleted. Are you sure to delete?\"")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes"){dialogInterface, which ->

            presenter.deleteFood(id)

        }
        builder.setNegativeButton("No",null)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showMessage(message: String) {
        runOnUiThread(Runnable { ->
            Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun finishActivity() {
        runOnUiThread(Runnable { ->
            finish()
        })

    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

}
