package com.bklndev.bengkelin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.bklndev.bengkelin.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
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
        val name = bundle?.getString("name_value") as String
        val email = bundle.getString("email_value") as String
        val phone = bundle.getString("phone_value") as String

        btn_register.setOnClickListener{
            val password: String = et_password.toString()
            val repassword: String = et_password_re.toString()

            //dialog loading
            val dialog = MaterialDialog(this)
                .customView(R.layout.loading)
                .cancelOnTouchOutside(false)
            dialog.show()
            if(TextUtils.isEmpty(password)||TextUtils.isEmpty(repassword)){
                Toast.makeText(this, "Please Fill all the Fileds", Toast.LENGTH_LONG).show()
            }else{
                registerUser(email, password,name,phone)
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
    private fun registerUser(email: String, password: String, name: String, phone: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    saveUserData(name,email,phone)
                }else{
                    Toast.makeText(this, "Registration Failed" +
                            it.getException(), Toast.LENGTH_LONG).show()
                }
            }
    }
    private fun saveUserData(name: String, email: String, phone: String){
        val uid = FirebaseAuth.getInstance().uid
        val db = FirebaseDatabase.getInstance().getReference("/user/$uid")

        db.setValue(User(name,email,phone))
            .addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
//                    nextToDashboard()
                }else{
                    Toast.makeText(this, "Registration Failed" +
                            it.getException(), Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener {
                //todo failure exception
            }
    }
}
