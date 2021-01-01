package com.example.usahaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_loginacitivity.*

class loginacitivity : AppCompatActivity() {
    private var googleSignInClient : GoogleSignInClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginacitivity)
        btnsignup.setOnClickListener{
            startActivity(Intent(this,signup::class.java))
            finish()
        }
    btnLogin.setOnClickListener{
        val email = inputEmail.text.toString()
        val password = inputPassword.text.toString()
        if (email.isEmpty()|| password.isEmpty()){
            Toast.makeText(this, "Masukkan Email and Password", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if (!it.isSuccessful){
                    return@addOnCompleteListener
                    val intent = Intent(this,loginacitivity::class.java)
                    startActivity(intent)
                } else
                    Toast.makeText(this,"Login Sukses", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener{
                Log.d("Main","Failed Login: ${it.message}")
                Toast.makeText(this,"Email/Password Salah", Toast.LENGTH_SHORT).show()
            } } }
}
