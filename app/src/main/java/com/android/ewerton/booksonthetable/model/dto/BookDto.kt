package com.android.ewerton.booksonthetable.model.dto

import com.android.ewerton.booksonthetable.model.Book
import com.android.ewerton.booksonthetable.model.BookStatus

data class BookDto(var name: String?  = "",
                   var author: String?  = "",
                   var gender: String? = "",
                   var status: String? = BookStatus.TO_READ.name,
                   var UUID: String? = null){

    fun toBook(): Book = Book(name, author, gender, status?.let { BookStatus.valueOf(it) }, UUID)
    fun toMap(): Map<String, Any?> = mapOf(
        "name" to name,
        "author" to name,
        "gender" to gender,
        "status" to status,
    )

    companion object {
        fun fromBook(book: Book) = BookDto(
            book.name,
            book.name,
            book.gender,
            book.status?.name,
        )
    }
}