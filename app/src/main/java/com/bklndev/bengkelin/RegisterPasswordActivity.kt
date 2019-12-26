package com.bklndev.bengkelin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register_password.*

class RegisterPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_password)

        auth = FirebaseAuth.getInstance()

        setSupportActionBar(findViewById(R.id.tb_register_password))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val bundle: Bundle? = intent.extras
        val name: String? = bundle?.getString("name_value")
        val email = bundle?.getString("email_value") as String

        btn_register.setOnClickListener{
            var password: String = et_password.toString()
            var repassword: String = et_password_re.toString()
            if(TextUtils.isEmpty(password)||TextUtils.isEmpty(repassword)){
                Toast.makeText(this, "Please Fill all the Fileds", Toast.LENGTH_LONG).show()
            }else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                        nextToDashboard()
                        finish()
                    }else{
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
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
