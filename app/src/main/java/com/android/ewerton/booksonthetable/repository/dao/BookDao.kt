package com.android.ewerton.booksonthetable.repository.dao

import androidx.room.*
import com.android.ewerton.booksonthetable.model.Book
import com.android.ewerton.booksonthetable.model.BookStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book): Long

    @Delete
    suspend fun delete(book: Book)

    @Update
    suspend fun update(book: Book)

    @Query("SELECT * FROM book WHERE book.id =:id")
    fun findById(id: Long): Flow<Book?>

    @Query("SELECT * FROM book WHERE book.status like :status")
    fun getAllBooksByStatus(status: BookStatus): Flow<MutableList<Book?>>

    @Query("DELETE FROM book")
    suspend fun clear()
}