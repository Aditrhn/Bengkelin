package com.bklndev.bengkelin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import kotlinx.android.synthetic.main.activity_register_password.*

class RegisterPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_password)
        setSupportActionBar(findViewById(R.id.tb_register_password))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        btn_register.setOnClickListener{
            nextToDashboard()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.register_menu, menu)
        return true
    }
    private fun nextToDashboard(){
        intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

}
