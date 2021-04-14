package com.solutis.ewerton.booksonthetable.ui.activity.internal.maintain_book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solutis.ewerton.booksonthetable.model.Book

class MaintainBookViewModel : ViewModel() {
    val book = MutableLiveData(Book())
}