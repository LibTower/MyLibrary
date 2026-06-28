package com.example.mylibrary.UI.Adapter

import com.example.mylibrary.model.Book
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R

class BookAdapter(
    private val books: List<Book>,
    private val onClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.titleText)
        val author = view.findViewById<TextView>(R.id.authorText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        holder.title.text = book.title
        holder.author.text = book.author

        holder.itemView.setOnClickListener {
            onClick(book)
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }
}