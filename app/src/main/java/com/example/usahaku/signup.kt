package com.example.usahaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_loginacitivity.*
import kotlinx.android.synthetic.main.activity_signup.*

class signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        btnregis.setOnClickListener {
            signUpUser()
        }
    }
    private fun signUpUser() {
        if (inputEmails.text.toString().isEmpty()) {
            inputEmails.error = "Please enter email"
            inputEmails.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS
                .matcher(inputEmails.text.toString()).matches()) {
            inputEmails.error = "Masukkan Email yang Benar"
            inputEmails.requestFocus()
            return
        }
        if (inputPasswords.text.toString().isEmpty()) {
            inputPasswords.error = "Masukkan Password"
            inputPasswords.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(inputEmails.text.toString(),
            inputPasswords.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this,
                                    loginacitivity::class.java))
                                finish()
                            }
                        }
                } else {
                    Toast.makeText(
                        baseContext, "Sign Up gagal. Coba Beberapa Saat Lagi.",
                        Toast.LENGTH_SHORT
                    ).show()
                 }
                }
            }
         }
