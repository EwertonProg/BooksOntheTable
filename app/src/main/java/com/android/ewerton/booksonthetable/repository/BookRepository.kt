package com.android.ewerton.booksonthetable.repository

import com.android.ewerton.booksonthetable.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun persist(book: Book): Book

    suspend fun delete(book: Book)

    fun findById(id: Long): Flow<Book?>

    fun getAllBooksReading(): Flow<MutableList<Book?>>

    fun getAllBooksRead(): Flow<MutableList<Book?>>

    fun getAllBooksToRead(): Flow<MutableList<Book?>>

    suspend fun clear()
}