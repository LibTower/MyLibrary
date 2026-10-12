package com.example.mylibrary.UI.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mylibrary.R
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passInput = findViewById<EditText>(R.id.passwordInput)
        val confirmPassInput = findViewById<EditText>(R.id.confirmPasswordInput)
        val regButton = findViewById<Button>(R.id.registerButton)
        val auth = FirebaseAuth.getInstance()

        regButton.setOnClickListener {
            if(passInput.text.toString() == confirmPassInput.text.toString()){
                auth.createUserWithEmailAndPassword(emailInput.text.toString(), passInput.text.toString())
                    .addOnCompleteListener {task ->
                        if(task.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                            return@addOnCompleteListener
                        }
                    }
            }
            else {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            }
        }
    }
}