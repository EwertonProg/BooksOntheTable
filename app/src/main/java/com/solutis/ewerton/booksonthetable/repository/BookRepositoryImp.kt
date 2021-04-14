package com.solutis.ewerton.booksonthetable.repository

import com.solutis.ewerton.booksonthetable.model.Book
import com.solutis.ewerton.booksonthetable.model.BookStatus
import com.solutis.ewerton.booksonthetable.repository.dao.BookDao

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

    override suspend fun findById(id: Long): Book? {
        return dao.findById(id)
    }

    override suspend fun getAllBooksReading(): MutableList<Book?> {
        return dao.getAllBooksByStatus(BookStatus.READING)
    }

    override suspend fun getAllBooksRead(): MutableList<Book?> {
       return dao.getAllBooksByStatus(BookStatus.READ)
    }

    override suspend fun getAllBooksToRead(): MutableList<Book?> {
       return dao.getAllBooksByStatus(BookStatus.TO_READ)
    }

    override suspend fun clear() {
        dao.clear()
    }

}