package com.android.ewerton.booksonthetable.repository.webservice

import com.android.ewerton.booksonthetable.model.User
import com.android.ewerton.booksonthetable.repository.util.USER_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore

class UserServiceImp(private val firestore: FirebaseFirestore) : UserService{
    override suspend fun saveUser(user: User) {
        firestore.collection(USER_COLLECTION).document(user.uid).set(mapOf("email" to user.email))
    }
}