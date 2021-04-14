package com.solutis.ewerton.booksonthetable.repository.webservice

import com.solutis.ewerton.booksonthetable.model.Book
import retrofit2.Call
import retrofit2.http.GET

interface BookService {
    @GET("users/{user}/books/reading")
    suspend fun getAllReadingBooksForUser(): Call<List<Book>>

    @GET("users/{user}/books/toRead")
    suspend fun getAllToReadBooksForUser(): Call<List<Book>>

    @GET("users/{user}/books/read")
    suspend fun getAllReadBooksForUser(): Call<List<Book>>

    @GET("users/{user}/books/{id}")
    suspend fun getUserBook(): Call<Book>
}