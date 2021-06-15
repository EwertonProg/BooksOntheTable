package com.android.ewerton.booksonthetable.repository.sharedPreferences

interface AppSharedPreferences {
    suspend fun saveSignedUserUid(uid: String)
    suspend fun getSignedUserUid(): String
}