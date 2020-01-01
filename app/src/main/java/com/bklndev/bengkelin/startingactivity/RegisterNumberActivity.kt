package com.bklndev.bengkelin.startingactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bklndev.bengkelin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_register_number.*

class RegisterNumberActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var name: String
    private lateinit var email: String
    private lateinit var phone: String
    private var verificationId: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_number)

        auth = FirebaseAuth.getInstance()

        val bundle: Bundle? = intent.extras
        name = bundle?.getString("name_value") as String
        email = bundle.getString("email_value") as String
        phone = bundle.getString("phone_value") as String
        verificationId = bundle.getString("code_value")

        btn_register_number_next.setOnClickListener {
            val smsCode = peet_pin_entry .text.toString()
            val credential = PhoneAuthProvider.getCredential(verificationId!!, smsCode)
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("PhoneAuthActivity", "signInWithCredential:success")

                    val user = task.result?.user
                    // ...
                    Log.d("PhoneAuthActivity", "User : $user")
                    nextToPassword()
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("PhoneAuthActivity", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }
    }
    private fun nextToPassword(){
        intent = Intent(this, RegisterPasswordActivity::class.java)
        intent.putExtra("name_value", name)
        intent.putExtra("email_value", email)
        intent.putExtra("phone_value", phone)
        startActivity(intent)
        finish()
    }
}
