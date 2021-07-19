package com.android.ewerton.booksonthetable.repository.webservice

import com.android.ewerton.booksonthetable.model.Book

interface BookService {
    suspend fun getAllBooksForUser(): List<Book?>
    suspend fun getBook(UUID:String): Book?
    suspend fun persistBook(book: Book): Book?
    suspend fun deleteBook(UUID: String)
}