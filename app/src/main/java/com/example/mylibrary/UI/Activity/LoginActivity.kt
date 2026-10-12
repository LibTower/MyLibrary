package com.example.mylibrary.UI.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mylibrary.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passInput = findViewById<EditText>(R.id.passwordInput)
        val logButton = findViewById<Button>(R.id.loginButton)
        val googleLogButton = findViewById<Button>(R.id.googleLoginButton)
        val goToReg = findViewById<TextView>(R.id.goToRegisterText)
        val auth = FirebaseAuth.getInstance()

        logButton.setOnClickListener {
            auth.signInWithEmailAndPassword(emailInput.text.toString(), passInput.text.toString())
                .addOnCompleteListener {task ->
                    if(task.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                        return@addOnCompleteListener
                    }
                }
        }

        goToReg.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}