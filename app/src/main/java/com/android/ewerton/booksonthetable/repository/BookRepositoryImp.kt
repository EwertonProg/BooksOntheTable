package com.android.ewerton.booksonthetable.repository

import com.android.ewerton.booksonthetable.model.Book
import com.android.ewerton.booksonthetable.model.BookStatus
import com.android.ewerton.booksonthetable.repository.dao.BookDao
import kotlinx.coroutines.flow.Flow

class BookRepositoryImp(private val dao: BookDao) : BookRepository {

    override suspend fun persist(book: Book): Book {
        return if (book.id == 0L) {
            book.apply {
                id = dao.insert(book)
            }
        } else {
            dao.update(book)
            book
        }
    }

    override suspend fun delete(book: Book) {
        dao.delete(book)
    }

    override fun findById(id: Long): Flow<Book?> {
        return dao.findById(id)
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