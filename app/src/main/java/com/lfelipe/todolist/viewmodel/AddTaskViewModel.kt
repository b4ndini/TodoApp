package com.lfelipe.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lfelipe.todolist.model.PostIt
import com.lfelipe.todolist.repository.MainRepository
import kotlinx.coroutines.launch

class AddTaskViewModel(application: Application, private val repository: MainRepository) : AndroidViewModel(application) {

    var postItLiveData: MutableLiveData<String> = MutableLiveData()
    var errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun insertPostIt(post: PostIt){
        viewModelScope.launch {
            val insert = repository.insertPostIt(post)
            if (insert.toInt() > 0) {
                postItLiveData.postValue("Post-it adicionado com sucesso")
            } else {
                errorLiveData.postValue("Não foi possível adicionar este Post-it")
            }
        }
    }
}