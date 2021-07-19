package com.android.ewerton.booksonthetable.repository

import com.android.ewerton.booksonthetable.model.User

interface UserRepository {
    suspend fun saveUser(user: User) :Boolean
    suspend fun login(user: User): Boolean
    suspend fun persist(user: User): User
}