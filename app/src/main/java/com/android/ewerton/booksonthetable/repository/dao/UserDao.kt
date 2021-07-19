package com.android.ewerton.booksonthetable.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.android.ewerton.booksonthetable.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User): Long

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM user WHERE user.email = :email AND user.password = :password")
    suspend fun getUserByNameAndPassword(email:String, password:String):User?

    @Query("DELETE FROM user")
    suspend fun clear()
}