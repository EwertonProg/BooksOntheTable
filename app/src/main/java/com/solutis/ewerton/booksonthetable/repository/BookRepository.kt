package com.solutis.ewerton.booksonthetable.repository

import com.solutis.ewerton.booksonthetable.model.Book

interface BookRepository {
    suspend fun insert(book: Book): Long

    suspend fun delete(book: Book)

    suspend fun update(book: Book)

    suspend fun findById(id: Long): Book?

    suspend fun getAllBooksReading(): MutableList<Book?>

    suspend fun getAllBooksRead(): MutableList<Book?>

    suspend fun getAllBooksToRead(): MutableList<Book?>

    suspend fun clear()
}