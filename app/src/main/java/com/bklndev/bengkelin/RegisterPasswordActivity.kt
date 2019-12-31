package com.bklndev.bengkelin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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
import java.util.regex.Pattern

class RegisterPasswordActivity : AppCompatActivity() {

    //firebaseauth
    private lateinit var auth: FirebaseAuth
    //dialog loading
    private lateinit var dialog: MaterialDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_password)

        auth = FirebaseAuth.getInstance()

        setSupportActionBar(findViewById(R.id.tb_register_password))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        dialog = MaterialDialog(this)
            .customView(R.layout.loading)
//            .cancelable(false)
            .cancelOnTouchOutside(false)

        val bundle: Bundle? = intent.extras
        val name = bundle?.getString("name_value") as String
        val email = bundle.getString("email_value") as String
        val phone = bundle.getString("phone_value") as String

        btn_register.setOnClickListener{
            val password = et_password .text.toString().trim()
            val repassword = et_password_re .text.toString().trim()



            if(TextUtils.isEmpty(password)){
                Toast.makeText(this, "Please Fill all the Fields", Toast.LENGTH_LONG).show()
            }else if(password != repassword){
                Toast.makeText(this, "The password you entered do not match. Please re-enter your password", Toast.LENGTH_LONG).show()
            }else if(password.length < 8){
                Toast.makeText(this, "The password should be at least 8 digits long", Toast.LENGTH_LONG).show()
            }else if(!isPasswordContainLetter(password)){
                Toast.makeText(this, "The password must contain letter", Toast.LENGTH_LONG).show()
            }else if(!isPasswordContainNumber(password)){
                Toast.makeText(this, "The password must contain at least 1 number", Toast.LENGTH_LONG).show()
            }else{
                //show loading dialog
                dialog.show()
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
    private fun registerUser(email: String, password: String, name: String, phone: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    saveUserData(name,email,phone)
                }else{
                    Toast.makeText(this, "Registration Failed" +
                            it.exception, Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }
            }
    }
    private fun saveUserData(name: String, email: String, phone: String){
        val uid = FirebaseAuth.getInstance().uid
        val db = FirebaseDatabase.getInstance().getReference("/user/$uid")

        db.setValue(User(name,email,phone))
            .addOnCompleteListener{
                if (it.isSuccessful){
                    dialog.dismiss()
                    MaterialDialog(this).show {
                        title(text = "Register Successfull")
                        message(text = "Please Login to continue")
                        positiveButton(text = "Login"){
                            nextToLogin()
                        }
                    }
                }else{
                    Toast.makeText(this, "Registration Failed" +
                            it.exception, Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }
            }
            .addOnFailureListener {
                //todo failure exception
            }
    }
    private fun isPasswordContainNumber(password: String): Boolean {
        val exp = ".*[0-9].*"
        val pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }
    private fun isPasswordContainLetter(password: String): Boolean{
        val exp = ".*[a-zA-Z].*"
        val pattern = Pattern.compile(exp)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }
    private fun nextToLogin(){
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
