package com.android.ewerton.booksonthetable.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.ewerton.booksonthetable.model.Book
import com.android.ewerton.booksonthetable.model.User
import com.android.ewerton.booksonthetable.repository.dao.BookDao
import com.android.ewerton.booksonthetable.repository.dao.UserDao
import com.android.ewerton.booksonthetable.repository.db.converters.BookStatusConverter

@Database(entities = [User::class, Book::class], version = 4, exportSchema = false)
@TypeConverters(BookStatusConverter::class)
abstract class BooksOnTheTableDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val bookDao: BookDao
}