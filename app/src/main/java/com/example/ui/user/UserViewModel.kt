package com.example.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.data.entities.User
import com.example.data.repositories.UserRepository
import com.example.network.Resource
import com.example.ui.shared.ScopedViewModel
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ScopedViewModel() {

    val users: LiveData<Resource<List<User>>> = userRepository.getUsers(viewModelScope, coroutineContext)

}