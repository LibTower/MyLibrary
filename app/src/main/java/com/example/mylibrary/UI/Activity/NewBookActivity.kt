package com.example.mylibrary.UI.Activity

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

        val book = BookRepository.getBookById(
            intent.getIntExtra("bookId", -1)
        )

        backButton.setOnClickListener {
            finish()
        }

        book?.let {
            title.setText(it.title)
            author.setText(it.author)
        }

        saveButton.setOnClickListener {

            val newBook = Book(
                id = book?.id ?: 0,
                title = title.text.toString(),
                author = author.text.toString()
            )

            if (book == null) {
                BookRepository.addBook(newBook)
            } else {
                BookRepository.updateMyBook(newBook)
            }

            finish()
        }
    }
}