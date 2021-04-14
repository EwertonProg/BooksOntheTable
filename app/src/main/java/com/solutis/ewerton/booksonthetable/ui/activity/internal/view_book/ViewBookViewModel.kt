package com.solutis.ewerton.booksonthetable.ui.activity.internal.view_book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solutis.ewerton.booksonthetable.model.Book

class ViewBookViewModel : ViewModel() {
    val book = MutableLiveData(Book())
}