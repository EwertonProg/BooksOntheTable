package com.android.ewerton.booksonthetable.repository.sharedPreferences

interface AppSharedPreferences {
    fun saveSignedUserUid(uid: String)
    fun getSignedUserUid(): String
}