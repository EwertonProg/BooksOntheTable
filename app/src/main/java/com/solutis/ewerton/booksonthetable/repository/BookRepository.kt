package com.solutis.ewerton.booksonthetable.repository

import com.solutis.ewerton.booksonthetable.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun insert(book: Book): Long

    suspend fun delete(book: Book)

    suspend fun update(book: Book)

    fun findById(id: Long): Flow<Book?>

    fun getAllBooksReading(): Flow<MutableList<Book?>>

    fun getAllBooksRead(): Flow<MutableList<Book?>>

    fun getAllBooksToRead(): Flow<MutableList<Book?>>

    suspend fun clear()
}