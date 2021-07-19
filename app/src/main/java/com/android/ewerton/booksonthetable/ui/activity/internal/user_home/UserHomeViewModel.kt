package com.android.ewerton.booksonthetable.ui.activity.internal.user_home

import androidx.lifecycle.*
import com.android.ewerton.booksonthetable.model.Book
import com.android.ewerton.booksonthetable.model.BookStatus
import com.android.ewerton.booksonthetable.repository.BookRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserHomeViewModel(private val bookRepository: BookRepository) : ViewModel() {
    val readingBooks: LiveData<MutableList<Book?>> = bookRepository.getAllBooksReading().asLiveData()
    val readBooks: LiveData<MutableList<Book?>> = bookRepository.getAllBooksRead().asLiveData()
    val booksToRead: LiveData<MutableList<Book?>> = bookRepository.getAllBooksToRead().asLiveData()

    fun getAllBooks(){
        viewModelScope.launch {
            bookRepository.getAllBooksFromRemote()
        }
    }

    fun populateDatabase(){
        viewModelScope.launch {
            bookRepository.persist(Book("Teste Para ler","da silva","Romance",BookStatus.TO_READ))
            delay(1000)
            bookRepository.persist(Book("Teste Lendo","de santana","Criminal",BookStatus.READING))
            delay(1000)
            bookRepository.persist(Book("Teste Lido","de souza","Policial",BookStatus.READ))
            delay(1000)
            bookRepository.persist(Book("Teste Para ler 1","da silva","Romance",BookStatus.TO_READ))
            delay(1000)
            bookRepository.persist(Book("Teste Lendo 1","de santana","Criminal",BookStatus.READING))
            delay(1000)
            bookRepository.persist(Book("Teste Lido 1","de souza","Policial",BookStatus.READ))
        }
    }
}