package com.bioenabletech.attendance.cm.Login

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bioenabletech.attendance.cm.Food.ManageFoodActivity
import com.bioenabletech.attendance.cm.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val et_username = findViewById(R.id.et_uid) as EditText
        val et_password = findViewById(R.id.et_password) as EditText
        val btn_login = findViewById(R.id.btn_login) as TextView
        val checkBox = findViewById(R.id.ch_show_pswd) as CheckBox


        btn_login.setOnClickListener{

            var username = et_username.text.toString()
            var password = et_password.text.toString()

            Log.e("Tag","Username and Password: "+username+","+password)

            if(username.equals("1234") && password.equals("1234"))
            {
                val intent = Intent(this,ManageFoodActivity::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_SHORT).show()
            }
        }

        checkBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            et_password.setTransformationMethod(
                if (b) HideReturnsTransformationMethod.getInstance() else PasswordTransformationMethod.getInstance()
            )
        })

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.title = "Login"
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
