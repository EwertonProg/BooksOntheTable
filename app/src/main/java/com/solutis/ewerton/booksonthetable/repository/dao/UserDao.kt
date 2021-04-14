package com.solutis.ewerton.booksonthetable.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.solutis.ewerton.booksonthetable.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM user WHERE user.email = :email AND user.password = :password")
    suspend fun getUserByNameAndPassword(email:String, password:String):User?

}