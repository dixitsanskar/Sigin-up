package com.example.signuptest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWebException
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {
    lateinit var etEmail: EditText
    lateinit var userName : EditText
    private lateinit var etPass : EditText
    private lateinit var btnSignUp : Button
    private lateinit var auth: FirebaseAuth
    lateinit var redirectLogin: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etEmail = findViewById(R.id.regemail)
        etPass = findViewById(R.id.regpass)
        userName = findViewById(R.id.reguser)
        btnSignUp = findViewById(R.id.signupbutton)
        redirectLogin = findViewById(R.id.textViewlogin)

        auth = FirebaseAuth.getInstance()
        btnSignUp.setOnClickListener {
            signUpUser()
        }
        redirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Email and password cannot be blank", Toast.LENGTH_LONG).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Successfully Signed Up", Toast.LENGTH_SHORT).show()
                finish()
            }
            else
            {
                Toast.makeText(this, "Signed Up failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}