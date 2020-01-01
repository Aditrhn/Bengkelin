package com.bklndev.bengkelin.startingactivity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import com.bklndev.bengkelin.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        btn_register.setOnClickListener {
            btnRegister()
        }
        btn_login.setOnClickListener {
            btnLogin()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.register_menu, menu)
        return true
    }

    private fun btnRegister(){
        intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun btnLogin(){
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}