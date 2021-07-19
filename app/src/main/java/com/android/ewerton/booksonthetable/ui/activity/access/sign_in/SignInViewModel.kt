package com.android.ewerton.booksonthetable.ui.activity.access.sign_in

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ewerton.booksonthetable.model.User
import com.android.ewerton.booksonthetable.repository.UserRepository
import com.android.ewerton.booksonthetable.repository.sharedPreferences.AppSharedPreferences
import com.android.ewerton.booksonthetable.ui.util.Event
import kotlinx.coroutines.launch

class SignInViewModel(val userRepository: UserRepository, private val sharedPreferences: AppSharedPreferences) : ViewModel() {
    val user = MutableLiveData(User())
    private val _signInResult = MutableLiveData<Event<Boolean>>()
    val signInResult: LiveData<Event<Boolean>>
        get() = _signInResult

    private val _hasLoggedUserResult = MutableLiveData<Event<Boolean>>()
    val hasLoggedUserResult: LiveData<Event<Boolean>>
        get() = _hasLoggedUserResult

    fun signIn() {
        viewModelScope.launch {
            user.value?.let {
                try {
                    _signInResult.value = Event(userRepository.login(it))
                } catch (e: SQLiteException) {
                    _signInResult.value = Event(false)
                }
            }
        }
    }

    fun hasLoggedUser() {
        viewModelScope.launch {
            _hasLoggedUserResult.value = Event(sharedPreferences.getSignedUserUid().isNotEmpty())
        }
    }

}