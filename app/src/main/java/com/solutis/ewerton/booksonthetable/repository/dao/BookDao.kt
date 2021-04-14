package com.solutis.ewerton.booksonthetable.repository.dao

import androidx.room.*
import com.solutis.ewerton.booksonthetable.model.Book
import com.solutis.ewerton.booksonthetable.model.BookStatus

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book): Long

    @Delete
    suspend fun delete(book: Book)

    @Update
    suspend fun update(book: Book)

    @Query("SELECT * FROM book WHERE book.id =:id")
    suspend fun findById(id: Long): Book?

    @Query("SELECT * FROM book WHERE book.status like :status")
    suspend fun getAllBooksByStatus(status: BookStatus): MutableList<Book?>

    @Query("DELETE FROM book")
    suspend fun clear()
}