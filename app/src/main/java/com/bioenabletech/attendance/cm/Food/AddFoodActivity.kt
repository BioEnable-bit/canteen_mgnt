package com.bioenabletech.attendance.cm.Food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.bioenabletech.attendance.cm.R
import com.bioenabletech.attendance.cm.app.App
import com.bioenabletech.attendance.cm.app.IApp
import com.bioenabletech.attendance.cm.database.FoodItem

class AddFoodActivity : AppCompatActivity() , FoodItemsView{

    private lateinit var foodName : EditText
    private lateinit var foodPrice : EditText
    private lateinit var foodItemsPresenter: FoodItemsPresenter
//    private var id : Int = 0
//    private  var name : String = ""
//    private var price : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        val iApp: IApp = applicationContext as App

        foodItemsPresenter = FoodItemsPresenter(iApp, this)

        foodName = findViewById(R.id.food_name)
        foodPrice = findViewById(R.id.food_price)

        val extras = intent.extras
        if (extras==null) {
            if (actionBar == null) title = "Add item"
            val actionBar = supportActionBar
            actionBar!!.title = "Add item"
            actionBar!!.setDisplayHomeAsUpEnabled(true)
        } else {
            val id = extras.getInt("id", -1)
            val name : String? = extras.getString("name")
            val price = extras.getInt("price",0)

            if (actionBar == null) title = "Update " + intent.getStringExtra("name")
            val actionBar = supportActionBar
            actionBar!!.title = "Update " + intent.getStringExtra("name")
            actionBar!!.setDisplayHomeAsUpEnabled(true)

            foodName.setText(name)
            foodPrice.setText(price.toString())
        }

        Log.e("TAG", "ID" + intent.getIntExtra("id", -1))
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_food_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){

            R.id.action_addfood->
            {
                addfood()

            }

            android.R.id.home->
            {
                onBackPressed()
            }


        }
        return super.onOptionsItemSelected(item)
    }

    fun addfood()
    {
        val fprice: Int = foodPrice.text.toString().toInt()
        val fname = foodName.text.toString()
        val extras = intent.extras

        if(extras==null) {
            val foodItems = arrayListOf(
                FoodItem(fname, fprice)
            )
            foodItemsPresenter.addfood(foodItems)

            Log.e("TAG", "addFood: " + fname + "." + fprice)
        }
        else
        {
            val id = extras.getInt("id")
            foodItemsPresenter.updatefood(id,fname,fprice)
        }
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    override fun showMessage(message: String) {
        runOnUiThread(Runnable { ->
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        })
    }

    override fun finishActivity() {
        runOnUiThread(Runnable { ->
            finish()
        })

    }


}
