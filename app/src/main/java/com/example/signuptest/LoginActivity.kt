package com.example.signuptest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
    private lateinit var redirectSignup: TextView
    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    lateinit var LoginButton: Button
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        redirectSignup = findViewById(R.id.textViewsignup)
        etEmail = findViewById(R.id.email)
        etPass = findViewById(R.id.pass)
        LoginButton = findViewById(R.id.loginbutton)

        auth = FirebaseAuth.getInstance()

        LoginButton.setOnClickListener {
            login()
        }
        redirectSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login() {

        val email = etEmail.text.toString()
        val pass = etPass.text.toString()

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this){
            if (it.isSuccessful)
            {
                Toast.makeText(this, "Successfully Logged IN", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this, "Log In failed", Toast.LENGTH_SHORT).show()

        }
    }
}