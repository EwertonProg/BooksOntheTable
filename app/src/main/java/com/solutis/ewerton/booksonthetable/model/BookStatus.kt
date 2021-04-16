package com.solutis.ewerton.booksonthetable.model

enum class BookStatus(val statusName: String) {
    TO_READ("Para Ler"), READING("Lendo"), READ("Lido");

    companion object{
        fun getByStatusName(statusName: String):BookStatus?{
            return values().find { bookStatus -> bookStatus.statusName == statusName }
        }
    }
}
