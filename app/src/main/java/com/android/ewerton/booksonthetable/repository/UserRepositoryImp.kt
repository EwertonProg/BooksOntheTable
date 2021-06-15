package com.android.ewerton.booksonthetable.repository

import com.android.ewerton.booksonthetable.model.User
import com.android.ewerton.booksonthetable.repository.dao.UserDao
import com.android.ewerton.booksonthetable.repository.sharedPreferences.AppSharedPreferences
import com.android.ewerton.booksonthetable.repository.webservice.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImp(
    private val userDao: UserDao,
    private val authService: AuthService,
    private val sharedPreferences: AppSharedPreferences
) : UserRepository {
    override suspend fun saveUser(user: User): Boolean {
        return withContext(Dispatchers.IO) {
            val hasSaved = authService.signUpOnFirebase(user)
            if (hasSaved) {
                userDao.insert(user)
            }
            hasSaved
        }
    }

    override suspend fun persist(user: User): User {
        return if (user.id == 0L) {
            user.apply {
                id = userDao.insert(user)
            }
        } else {
            userDao.update(user)
            user
        }
    }

    override suspend fun login(user: User): Boolean {
        return withContext(Dispatchers.IO) {
            authService.signInOnFirebase(user)?.let { uid ->
                sharedPreferences.saveSignedUserUid(uid)
                persistUserWithUid(user, uid)
                return@withContext true
            }
            userDao.getUserByNameAndPassword(user.email, user.password)?.let {
                sharedPreferences.saveSignedUserUid(it.uid)
                return@withContext true
            }
            return@withContext false
        }
    }

    private suspend fun persistUserWithUid(
        user: User,
        uid: String
    ) {
        userDao.getUserByNameAndPassword(user.email, user.password)?.let {
            it.uid = uid
            this@UserRepositoryImp.persist(it)
            return
        }
        user.let {
            it.uid = uid
            this@UserRepositoryImp.persist(it)
            return
        }
    }
}