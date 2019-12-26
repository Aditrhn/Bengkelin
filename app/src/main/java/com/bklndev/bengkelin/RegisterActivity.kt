package com.bklndev.bengkelin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
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
        var name: String = et_name.toString()
        var email: String = et_email.toString()

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Successfully add Name and Email", Toast.LENGTH_LONG).show()

            intent = Intent(this, RegisterPasswordActivity::class.java)
            intent.putExtra("name_value", name)
            intent.putExtra("email_value", email)
            startActivity(intent)
            finish()
        }
    }
}
