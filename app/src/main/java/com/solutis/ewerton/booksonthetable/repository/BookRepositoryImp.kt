package com.solutis.ewerton.booksonthetable.repository

import com.solutis.ewerton.booksonthetable.model.Book
import com.solutis.ewerton.booksonthetable.model.BookStatus
import com.solutis.ewerton.booksonthetable.repository.dao.BookDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class BookRepositoryImp(private val dao: BookDao) : BookRepository{
    override suspend fun insert(book: Book): Long {
        return dao.insert(book)
    }

    override suspend fun delete(book: Book) {
        dao.delete(book)
    }

    override suspend fun update(book: Book) {
        dao.update(book)
    }

    override fun findById(id: Long): Flow<Book?> {
        return dao.findById(id).distinctUntilChanged()
    }

    override fun getAllBooksReading(): Flow<MutableList<Book?>> {
        return dao.getAllBooksByStatus(BookStatus.READING)
    }

    override fun getAllBooksRead(): Flow<MutableList<Book?>> {
       return dao.getAllBooksByStatus(BookStatus.READ)
    }

    override fun getAllBooksToRead(): Flow<MutableList<Book?>> {
       return dao.getAllBooksByStatus(BookStatus.TO_READ)
    }

    override suspend fun clear() {
        dao.clear()
    }

}