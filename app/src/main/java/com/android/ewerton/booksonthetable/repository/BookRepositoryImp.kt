package com.android.ewerton.booksonthetable.repository

import com.android.ewerton.booksonthetable.model.Book
import com.android.ewerton.booksonthetable.model.BookStatus
import com.android.ewerton.booksonthetable.repository.dao.BookDao
import com.android.ewerton.booksonthetable.repository.webservice.BookService
import kotlinx.coroutines.flow.Flow

class BookRepositoryImp(private val dao: BookDao, private val service: BookService) : BookRepository {

    override suspend fun persist(book: Book): Book? {
        return service.persistBook(book)?.let { savedBook ->
            persistLocally(savedBook)
        }
    }

    private suspend fun persistLocally(
        savedBook: Book
    ) = savedBook.apply {
        id = dao.insert(savedBook)
    }


    override suspend fun delete(book: Book) {
        book.UUID?.let { uuid ->
            service.deleteBook(uuid)
            dao.delete(book)
        }
    }

    override suspend fun getBookByUUID(UUID: String): Flow<Book?> {
        return dao.findByUUID(UUID)
    }

    override suspend fun getAllBooksFromRemote() {
        val books = service.getAllBooksForUser()
        books.forEach {
            it?.let {
                persistLocally(it)
            }
        }
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