package com.solutis.ewerton.booksonthetable.ui.activity.internal.view_book

import androidx.lifecycle.*
import com.solutis.ewerton.booksonthetable.model.Book
import com.solutis.ewerton.booksonthetable.model.BookStatus
import com.solutis.ewerton.booksonthetable.repository.BookRepository
import com.solutis.ewerton.booksonthetable.ui.util.Event
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewBookViewModel(private val bookRepository: BookRepository) : ViewModel() {
   private val _book = MutableLiveData(Book())
    val book: LiveData<Book?>
        get() = _book

    private val _deleteReturn = MutableLiveData<Event<Boolean>>()
    val deleteReturn: LiveData<Event<Boolean>>
        get() = _deleteReturn

    fun deleteBook() {
        viewModelScope.launch {
            book.value?.let { bookRepository.delete(it) }
            _deleteReturn.value = Event(true)
        }
    }

    fun getBook(id: Long){
        viewModelScope.launch {
            bookRepository.findById(id).collect {
                _book.value = it
            }
        }
    }

    fun changeBookStatus() {
        viewModelScope.launch {
            book.value?.run {
                status = status?.let { BookStatus.values()[it.ordinal + 1] }
                bookRepository.persist(this)
            }
        }
    }
}