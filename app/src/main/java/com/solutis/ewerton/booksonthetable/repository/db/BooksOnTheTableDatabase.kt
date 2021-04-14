package com.solutis.ewerton.booksonthetable.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.solutis.ewerton.booksonthetable.model.Book
import com.solutis.ewerton.booksonthetable.model.User
import com.solutis.ewerton.booksonthetable.repository.dao.BookDao
import com.solutis.ewerton.booksonthetable.repository.dao.UserDao
import com.solutis.ewerton.booksonthetable.repository.db.converters.BookStatusConverter

@Database(entities = [User::class, Book::class], version = 3, exportSchema = false)
@TypeConverters(BookStatusConverter::class)
abstract class BooksOnTheTableDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val bookDao: BookDao
}