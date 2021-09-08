package com.lfelipe.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lfelipe.todolist.model.PostIt
import com.lfelipe.todolist.repository.MainRepository
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

    var errorLiveData: MutableLiveData<List<PostIt>> = MutableLiveData()
    var postItLiveData: MutableLiveData<List<PostIt>> = MutableLiveData()
    private val repository: MainRepository = MainRepository(application)


    fun getPostIts() {
        viewModelScope.launch {
            val postList = repository.getPostIts()
            if(postList.isNotEmpty()){
                postItLiveData.postValue(postList)
            }else{
                errorLiveData.postValue(postList)
            }
        }
    }


}