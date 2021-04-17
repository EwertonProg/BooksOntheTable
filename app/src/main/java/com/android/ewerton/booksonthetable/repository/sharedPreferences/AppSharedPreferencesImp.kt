package com.android.ewerton.booksonthetable.repository.sharedPreferences

import android.content.SharedPreferences
import com.android.ewerton.booksonthetable.repository.util.USER_UID

class AppSharedPreferencesImp(private val sharedPreferences: SharedPreferences):AppSharedPreferences{
    override fun saveSignedUserUid(uid: String) {
        with (sharedPreferences.edit()) {
            putString(USER_UID, uid)
            apply()
        }
    }

    override fun getSignedUserUid(): String {
        return sharedPreferences.getString(USER_UID, "") ?: ""
    }

}