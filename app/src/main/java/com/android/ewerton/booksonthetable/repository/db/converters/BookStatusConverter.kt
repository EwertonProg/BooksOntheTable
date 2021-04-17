package com.android.ewerton.booksonthetable.repository.db.converters

import androidx.room.TypeConverter
import com.android.ewerton.booksonthetable.model.BookStatus

class BookStatusConverter {
    @TypeConverter
    fun toBookStatus(value: String) = enumValueOf<BookStatus>(value)

    @TypeConverter
    fun fromBookStatus(value: BookStatus) = value.name
}