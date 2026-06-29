package com.example.mylibrary.UI.Adapter

import com.example.mylibrary.model.Book
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R

class BookAdapter(
    private val books: MutableList<Book>,
    private val onItemClick: (Book) -> Unit,
    private val onItemDelete: (Int) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.titleText)
        val author = view.findViewById<TextView>(R.id.authorText)
        val delete = view.findViewById<ImageButton>(R.id.delete)
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
            onItemClick(book)
        }

        holder.delete.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                val bookToDelete = books[pos]

                onItemDelete(bookToDelete.id)

                books.removeAt(pos)
                notifyItemRemoved(pos)
            }
        }
    }

    override fun getItemCount(): Int = books.size
}