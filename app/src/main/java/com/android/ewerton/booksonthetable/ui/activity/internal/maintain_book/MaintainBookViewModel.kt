package com.android.ewerton.booksonthetable.ui.activity.internal.maintain_book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ewerton.booksonthetable.model.Book
import com.android.ewerton.booksonthetable.repository.BookRepository
import com.android.ewerton.booksonthetable.ui.util.Event
import kotlinx.coroutines.launch

class MaintainBookViewModel(private val bookRepository: BookRepository) : ViewModel() {
    val book = MutableLiveData(Book())
    private val _persistReturn = MutableLiveData<Event<Boolean>>()
    val persistReturn: LiveData<Event<Boolean>>
        get() = _persistReturn

    fun persistBook(){
        viewModelScope.launch {
            book.value?.let { bookRepository.persist(it) }
            _persistReturn.value = Event(true)
        }
    }



    fun getBookGenders() = listOf(
        "Romance",
        "Drama",
        "Conto",
        "Poesia",
        "Biografia",
        "Aventura",
        "Terror",
        "Literatura fantástica",
        "Ficção",
        "HQ"
    )
}