package com.solutis.ewerton.booksonthetable.ui.activity.internal.user_home

import androidx.lifecycle.*
import com.solutis.ewerton.booksonthetable.model.Book
import com.solutis.ewerton.booksonthetable.model.BookStatus
import com.solutis.ewerton.booksonthetable.repository.BookRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserHomeViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private var _readingBooks = MutableLiveData<MutableList<Book?>>()
    val readingBooks: LiveData<MutableList<Book?>>
        get() = _readingBooks

    private var _readBooks = MutableLiveData<MutableList<Book?>>()
    val readBooks: LiveData<MutableList<Book?>>
        get() = _readBooks

    private var _booksToRead = MutableLiveData<MutableList<Book?>>()
    val booksToRead: LiveData<MutableList<Book?>>
        get() = _booksToRead

    fun getAllBooksReading(){
        viewModelScope.launch {
             bookRepository.getAllBooksReading().collect {
                 _readingBooks.value = it
            }
        }
    }

    fun getAllBooksRead(){
        viewModelScope.launch {
             bookRepository.getAllBooksRead().collect {
            _readBooks.value = it
            }
        }
    }

    fun getAllBooksToRead(){
        viewModelScope.launch {
             bookRepository.getAllBooksToRead().collect {
                 _booksToRead.value = it
            }
        }
    }

    fun populateDatabase(){
        viewModelScope.launch {
            bookRepository.insert(Book("Teste Para ler","da silva","Romance",BookStatus.TO_READ,1))
            bookRepository.insert(Book("Teste Lendo","de santana","Criminal",BookStatus.READING,2))
            bookRepository.insert(Book("Teste Lido","de souza","Policial",BookStatus.READ,3))
            bookRepository.insert(Book("Teste Para ler 1","da silva","Romance",BookStatus.TO_READ,4))
            bookRepository.insert(Book("Teste Lendo 1","de santana","Criminal",BookStatus.READING,5))
            bookRepository.insert(Book("Teste Lido 1","de souza","Policial",BookStatus.READ,6))
        }
    }
}