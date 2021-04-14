package com.solutis.ewerton.booksonthetable.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user", indices = [Index(value = ["email"],unique = true)])
data class User(
    @ColumnInfo(name = "email")
    var email: String = "",
    @ColumnInfo(name = "password")
    var password: String = "",
    @ColumnInfo(name = "name")
    var name: String = "",
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
){
}