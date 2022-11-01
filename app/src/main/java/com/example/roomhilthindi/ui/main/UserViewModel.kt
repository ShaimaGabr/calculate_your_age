package com.example.roomhilthindi.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomhilthindi.model.User
import com.example.roomhilthindi.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class UserViewModel
@ViewModelInject
constructor(private val userRepository: UserRepository) : ViewModel() {
    val getUserData: LiveData<List<User>> = userRepository.getUserData
        .flowOn(Dispatchers.Main).asLiveData(context = viewModelScope.coroutineContext)

    fun insert(user: User) = viewModelScope.launch {
        userRepository.insert(user)
    }

    fun deletbyid(user: User) = viewModelScope.launch {
        userRepository.deletbyid(user)
    }
}