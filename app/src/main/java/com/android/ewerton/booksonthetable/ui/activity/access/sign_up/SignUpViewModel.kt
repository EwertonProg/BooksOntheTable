package com.android.ewerton.booksonthetable.ui.activity.access.sign_up

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ewerton.booksonthetable.model.User
import com.android.ewerton.booksonthetable.repository.UserRepository
import com.android.ewerton.booksonthetable.ui.util.Event
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
                    _signUpResult.value = Event(userRepository.saveUser(it))
                } catch (e: SQLiteException) {
                    _signUpResult.value = Event(false)
                }
            }
        }
    }
}