package com.lfelipe.todolist.di

import com.lfelipe.todolist.repository.MainRepository
import com.lfelipe.todolist.viewmodel.AddTaskViewModel
import com.lfelipe.todolist.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val postItModule = module {

    single {
        MainRepository(get())
    }

    viewModel {
        MainViewModel(
            get(),
            get()
        )
    }

    viewModel {
        AddTaskViewModel(
            get(),
            get()
        )
    }

}