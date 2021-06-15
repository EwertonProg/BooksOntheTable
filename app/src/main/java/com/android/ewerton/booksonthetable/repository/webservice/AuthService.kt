package com.android.ewerton.booksonthetable.repository.webservice

import com.android.ewerton.booksonthetable.model.User

interface AuthService{
    suspend fun signUpOnFirebase(user: User) : Boolean
    suspend fun signInOnFirebase(user: User) : String?
}