package com.android.ewerton.booksonthetable.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user", indices = [Index(value = ["email"],unique = true), Index(value = ["uid"],unique = true)])
data class User(
    @ColumnInfo(name = "email")
    var email: String = "",
    @ColumnInfo(name = "password")
    var password: String = "",
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "uid")
    var uid: String = "",
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
)