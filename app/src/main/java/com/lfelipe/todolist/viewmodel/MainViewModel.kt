package com.lfelipe.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lfelipe.todolist.model.PostIt
import com.lfelipe.todolist.repository.MainRepository
import kotlinx.coroutines.launch


class MainViewModel(application: Application, private val repository: MainRepository) : AndroidViewModel(application) {

    var errorLiveData: MutableLiveData<List<PostIt>> = MutableLiveData()
    var postItLiveData: MutableLiveData<List<PostIt>> = MutableLiveData()


    fun getPostIts() {
        viewModelScope.launch {
            val postList = repository.getPostIts()
            if (postList.isNotEmpty()) {
                postItLiveData.postValue(postList)
            } else {
                errorLiveData.postValue(postList)
            }
        }
    }

    fun deletePostIt(postTitle: String){
        viewModelScope.launch {
            val success = repository.deletePostIt(postTitle)
            if (success > 0)
                getPostIts()
        }
    }

}