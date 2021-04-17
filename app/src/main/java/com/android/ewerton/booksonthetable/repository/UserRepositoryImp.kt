package com.android.ewerton.booksonthetable.repository

import com.android.ewerton.booksonthetable.model.User
import com.android.ewerton.booksonthetable.repository.dao.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImp(private val userDao: UserDao) :UserRepository {
    override suspend fun saveUser(user: User) :Long {
       return withContext(Dispatchers.IO) {
            userDao.insert(user)
        }
    }

    override suspend fun login(user: User): Boolean {
        return withContext(Dispatchers.IO) {
            if (userDao.getUserByNameAndPassword(user.email, user.password) == null) {
                return@withContext false
            }
            return@withContext true
        }
    }
}