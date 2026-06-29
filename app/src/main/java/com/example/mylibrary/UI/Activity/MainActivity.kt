package com.example.mylibrary.UI.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R
import com.example.mylibrary.UI.Adapter.BookAdapter
import com.example.mylibrary.repository.BookRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val createNewBookButton = findViewById<Button>(R.id.AddBook)

        createNewBookButton.setOnClickListener {
            startActivity(Intent(this, NewBookActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        val recyclerView = findViewById<RecyclerView>(R.id.YourBooks)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = BookAdapter(
            BookRepository.getBooks().toMutableList(),
            { book ->
                val intent = Intent(this, NewBookActivity::class.java)
                intent.putExtra("bookId", book.id)
                startActivity(intent)
            },
            { id ->
                BookRepository.delBook(id)
            }
        )
    }
}