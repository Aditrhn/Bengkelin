package com.bklndev.bengkelin.startingactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import android.util.Log
import com.bklndev.bengkelin.R
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit


class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var phone: String
    private var codeSent: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        setSupportActionBar(findViewById(R.id.tb_register))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        btn_register_next.setOnClickListener {
            sendVerificationCode()
        }
        callbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                Log.w("PhoneAuthActivity", "onVerificationCompleted:$phoneAuthCredential")
                authToPassword(phoneAuthCredential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w("PhoneAuthActivity", "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                    Log.w("PhoneAuthActivity", "Invalid Request", e)
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                    Log.w("PhoneAuthActivity", "Quota exceeded", e)
                }
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verificationId, token)
                codeSent = verificationId

                Log.d("PhoneAuthActivity","phone : $phone verif id : $codeSent")
                nextToNumber()
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
    private fun nextToNumber(){
            intent = Intent(this, RegisterNumberActivity::class.java)
            intent.putExtra("name_value", name)
            intent.putExtra("email_value", email)
            intent.putExtra("phone_value", phone)
            intent.putExtra("code_value", codeSent)
            startActivity(intent)
    }

    private fun nextToPassword(){
        intent = Intent(this, RegisterPasswordActivity::class.java)
        intent.putExtra("name_value", name)
        intent.putExtra("email_value", email)
        intent.putExtra("phone_value", phone)
        startActivity(intent)
        finish()
    }

    private fun authToPassword(credential: PhoneAuthCredential){
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

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun sendVerificationCode(){
        name = et_name .text.toString().trim()
        email = et_email .text.toString().trim()
        phone = et_phone .text.toString().trim()

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show()
        }else if(!isEmailValid(email)){
            Toast.makeText(this, "Please provide valid email address", Toast.LENGTH_LONG).show()
        }else {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone, // Phone number to verify
                30, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                this, // Activity (for callback binding)
                callbacks
            ) // OnVerificationStateChangedCallbacks
        }
    }
}
