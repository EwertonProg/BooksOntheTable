package com.android.ewerton.booksonthetable.repository.webservice

import com.android.ewerton.booksonthetable.model.User

interface UserService{
    suspend fun saveUser(user: User)
}