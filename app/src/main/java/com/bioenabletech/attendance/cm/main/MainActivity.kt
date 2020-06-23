package com.bioenabletech.attendance.cm.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bioenabletech.attendance.cm.Login.LoginActivity
import com.bioenabletech.attendance.cm.R
import com.bioenabletech.attendance.cm.Transaction.TransactionsAdapter
import com.bioenabletech.attendance.cm.app.IApp
import com.bioenabletech.attendance.cm.database.FoodItem
import com.bioenabletech.attendance.cm.database.Sale
import com.bioenabletech.attendance.cm.database.Transaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),MainActivityView {

    private lateinit var app : IApp
    var total:Int = 0
    val adapter = FoodItemsAdapter( )
    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = applicationContext as IApp
        presenter= MainActivityPresenter(app,this)

        setContentView(R.layout.activity_main)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.title = "Menu Items"
        actionBar!!.title = "Canteen Management"
        // actionBar.setDisplayHomeAsUpEnabled(true)

        val rv = findViewById<RecyclerView>(R.id.rv_food_items)
        val pay = findViewById<Button>(R.id.btn_pay)

        rv.layoutManager = LinearLayoutManager(this)

        val adapter1 = TransactionsAdapter( )
        rv.adapter = adapter

        app.database().foodItemDao().getAll().observe(this, object : Observer<List<FoodItem>>{


            override fun onChanged(t: List<FoodItem>) {
                Log.e("Trasanction",""+t)
                    adapter.setFoodItems(t)
            }
        })

        app.database().saleDao().getAll().observe(this, object : Observer<List<Sale>>{


            override fun onChanged(t: List<Sale>) {
                Log.e("Sales",""+t)

            }
        })


        btn_pay.setOnClickListener {
            val items = adapter.getFoodSelection()
            val msg = StringBuilder()

            items.forEach {
                if(!msg.isEmpty())msg.append("\n")
                val countWisePrice: Int
                countWisePrice = it.price*it.count
                val line = "${it.name}: ${it.price}x${it.count} : ${countWisePrice}"

                total = total +countWisePrice;

                msg.append(line)
            }
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Summary")
            builder.setMessage(msg.toString()+"\n\nTotal : $total")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { dialogInterface, which ->

                val intent1 = Intent("in.bioenabletech.attendance.operations.Identify")
                startActivityForResult(intent1, 1)
            }
            builder.setNegativeButton("No", null)
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_login->
            {
               val intent = Intent(this,
                   LoginActivity::class.java)
                startActivity(intent)
            }
        }


        return super.onOptionsItemSelected(item)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var currentDate = System.currentTimeMillis()
        var username : String? = null
        var uuid : String
        var transactionId : Int = 0
        app.database().transactionDao().getTransactionId().observe(this, object : Observer<Int> {
            override fun onChanged(t: Int) {
                 transactionId = t
            }

        })

        when (requestCode){

            1 -> {
                if (data?.extras !=null)
                {
                    username = data?.extras?.getString("username")
                    uuid = data?.extras?.getString("uid").toString()
                    val trasaction = arrayListOf(Transaction(uuid,total, currentDate))
                    presenter.addTransaction(trasaction)
                    val builder = AlertDialog.Builder(this@MainActivity)
                    builder.setTitle("Authentication successfully done")
                    builder.setMessage("Username : $username\nUser ID : $uuid")
                    builder.setIcon(android.R.drawable.ic_dialog_alert)
                    builder.setPositiveButton("Ok") { dialogInterface, which ->


                        Log.e("lll","lll"+transactionId)

                        val items = adapter.getFoodSelection()
                        var sales : List<Sale>
                        items.forEach {

                            sales = arrayListOf(Sale(transactionId,it.id,it.price,it.count,currentDate))
                            presenter.addSale(sales)

                            Log.e("Tag","Sale"+sales)
                        }
                    }
                  //  builder.setNegativeButton("Ok", null)
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }
                else{
                    val builder = AlertDialog.Builder(this@MainActivity)
                    builder.setTitle("Try Again")
                    builder.setMessage("You are not authenticated")
                    builder.setIcon(android.R.drawable.ic_dialog_alert)

                    builder.setPositiveButton("Yes") { dialogInterface, which ->

                        val intent1 = Intent("in.bioenabletech.attendance.operations.Identify")
                        startActivityForResult(intent1, 1)

                    }

                    builder.setNegativeButton("Ok", null)
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()

                }
            }
        }



    }



    override fun showMessage(message: String) {
        runOnUiThread(Runnable {
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        })
    }

    override fun finishActivity() {
         finish()
    }


}
