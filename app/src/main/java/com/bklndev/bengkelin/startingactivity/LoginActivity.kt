package com.bklndev.bengkelin.startingactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bklndev.bengkelin.DashboardActivity
import com.bklndev.bengkelin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        setSupportActionBar(findViewById(R.id.tb_login))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        btn_login.setOnClickListener {
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            loginUser(email,password)
        }

    }

    private fun loginUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("loginuser", "signInWithEmail:success")
                    val user = auth.currentUser
                    nextToDashboard(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("loginuser", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

                // ...
            }
    }

    private fun nextToDashboard(user: FirebaseUser?){
        intent = Intent(this, DashboardActivity::class.java)
        intent.putExtra("user_value", user)
        startActivity(intent)
        finish()
    }
}
