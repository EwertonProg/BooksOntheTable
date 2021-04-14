package com.solutis.ewerton.booksonthetable.ui.activity.access.sign_in

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solutis.ewerton.booksonthetable.model.User
import com.solutis.ewerton.booksonthetable.repository.UserRepository
import com.solutis.ewerton.booksonthetable.ui.util.Event
import kotlinx.coroutines.launch

class SignInViewModel(val userRepository: UserRepository): ViewModel(){
    val user = MutableLiveData(User())
    private val _signInResult = MutableLiveData<Event<Boolean>>()
    val signInResult: LiveData<Event<Boolean>>
        get() = _signInResult

    fun signIn(){
        viewModelScope.launch {
            user.value?.let {
                try {
                    _signInResult.value = Event(userRepository.login(it))
                }catch (e: SQLiteException){
                    _signInResult.value = Event(false)
                }
            }
        }
    }
}