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
import com.example.mylibrary.model.Book
import com.example.mylibrary.repository.BookRepository

class NewBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_book)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton = findViewById<Button>(R.id.BackButton)
        val saveButton = findViewById<Button>(R.id.SaveButton)
        val title = findViewById<EditText>(R.id.BookTitle)
        val author = findViewById<EditText>(R.id.BookAuthor)

        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        saveButton.setOnClickListener {
            BookRepository.addBook(
                Book(
                    id = 0,
                    title.text.toString(),
                    author.text.toString()
                )
            )
            finish()
        }
    }
}