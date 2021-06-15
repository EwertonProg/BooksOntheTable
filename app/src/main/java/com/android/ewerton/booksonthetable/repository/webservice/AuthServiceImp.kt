package com.android.ewerton.booksonthetable.repository.webservice

import com.android.ewerton.booksonthetable.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthServiceImp(private val auth: FirebaseAuth):AuthService{
    override suspend fun signUpOnFirebase(user: User) : Boolean{
        return suspendCoroutine {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { loginReturn ->
                it.resume(loginReturn.isSuccessful)
            }
        }
    }

    override suspend fun signInOnFirebase(user: User) :String? {
       return suspendCoroutine {
            auth.signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { loginReturn ->
                if (loginReturn.isSuccessful) {
                    it.resume(auth.currentUser!!.uid)
                }else{
                    it.resume(null)
                }
            }
        }
    }
}