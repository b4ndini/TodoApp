package com.lfelipe.todolist.myapplication

import android.app.Application
import com.lfelipe.todolist.di.postItModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@MyApp)
            modules(postItModule)
        }
    }

}