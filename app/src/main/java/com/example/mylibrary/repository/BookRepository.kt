package com.example.mylibrary.repository

import com.example.mylibrary.model.Book

object BookRepository {
    private val books = mutableListOf<Book>()
    private var nextId = 1


    fun addBook(book: Book){
        books.add(book.copy(id=nextId++))
    }

    fun getBooks(): List<Book> {
        return books
    }

    fun updateBook(updateBook: Book){
        val index = books.indexOfFirst { it.id == updateBook.id }
        if (index != -1) {
            books[index] = updateBook
        }
    }
}