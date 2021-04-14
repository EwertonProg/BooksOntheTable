package com.solutis.ewerton.booksonthetable.ui.activity.access.sign_up

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solutis.ewerton.booksonthetable.model.User
import com.solutis.ewerton.booksonthetable.repository.UserRepository
import com.solutis.ewerton.booksonthetable.ui.util.Event
import kotlinx.coroutines.launch

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {
    val user = MutableLiveData(User())
    private val _signUpResult = MutableLiveData<Event<Boolean>>()
    val signUpResult: LiveData<Event<Boolean>>
            get() = _signUpResult

    fun signUpUser() {
        viewModelScope.launch {
            user.value?.let {
                try {
                    it.id = userRepository.saveUser(it)
                    _signUpResult.value = Event(it.id > 0)
                } catch (e: SQLiteException) {
                    _signUpResult.value = Event(false)
                }
            }
        }
    }
}