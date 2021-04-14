package com.solutis.ewerton.booksonthetable.repository

import com.solutis.ewerton.booksonthetable.model.User

interface UserRepository {
    suspend fun saveUser(user: User) :Long
    suspend fun login(user: User): Boolean
}