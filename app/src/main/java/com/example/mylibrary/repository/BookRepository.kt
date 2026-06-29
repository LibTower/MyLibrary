package com.example.mylibrary.repository

import com.example.mylibrary.model.Book

object BookRepository {
    private val books = mutableListOf<Book>()
    private var nextId = 1


    fun addBook(book: Book){
        books.add(book.copy(id=nextId++))
    }

    fun delBook(idBook: Int) {
        val index = books.indexOfFirst { it.id == idBook }

        if (index != -1) {
            books.removeAt(index)
        }
    }

    fun getBooks(): List<Book> {
        return books
    }

    fun updateMyBook(updateBook: Book){
        val index = books.indexOfFirst { it.id == updateBook.id }
        if (index != -1) {
            books[index] = updateBook
        }
    }

    fun getBookById(idBook: Int): Book? {
       return books.find { book -> book.id == idBook}
    }
}