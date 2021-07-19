package com.android.ewerton.booksonthetable.model

import com.google.gson.annotations.SerializedName

enum class BookStatus(val statusName: String) {
    @SerializedName("TO_READ")
    TO_READ("Para Ler"),

    @SerializedName("READING")
    READING("Lendo"),

    @SerializedName("READ")
    READ("Lido");

    companion object{
        fun getByStatusName(statusName: String):BookStatus?{
            return values().find { bookStatus -> bookStatus.statusName == statusName }
        }
    }
}
