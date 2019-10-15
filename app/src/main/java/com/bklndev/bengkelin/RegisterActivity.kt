package com.bklndev.bengkelin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(findViewById(R.id.tb_register))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        btn_register_next.setOnClickListener {
            nextToPassword()
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
    private fun nextToPassword(){
        intent = Intent(this, RegisterPasswordActivity::class.java)
        startActivity(intent)
    }
}
