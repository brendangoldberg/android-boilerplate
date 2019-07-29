package com.example.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.repositories.NameRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val nameRepository: NameRepository
) : ViewModel() {

    private val _name = MutableLiveData<String>().apply { postValue(nameRepository.getName()) }

    val name: LiveData<String> get() = _name

    fun setName(name: String) {
        _name.postValue(name)
    }

}