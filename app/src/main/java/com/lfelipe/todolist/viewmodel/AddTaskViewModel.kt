package com.lfelipe.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.lfelipe.todolist.model.PostIt
import com.lfelipe.todolist.repository.MainRepository
import kotlinx.coroutines.launch

class AddTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MainRepository = MainRepository(application)

    fun insertPostIt(post: PostIt){
        viewModelScope.launch {
            repository.insertPostIt(post)
        }
    }
}