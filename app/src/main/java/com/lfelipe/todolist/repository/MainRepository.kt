package com.lfelipe.todolist.repository

import android.content.Context
import com.lfelipe.todolist.database.PostItDatabase
import com.lfelipe.todolist.model.PostIt
import java.lang.Exception

class MainRepository(context: Context) {

    val database: PostItDatabase by lazy { PostItDatabase.getInstance(context) }

    suspend fun getPostIts(): List<PostIt>{

        return try{
            val postItList = database.DAO().getPostIts()

            if(postItList.isNotEmpty()){
                postItList
            }
            else{
                val error = listOf<PostIt>()
                error
            }

        }catch (exception: Exception){
            val error = listOf<PostIt>()
            error
        }

    }

    suspend fun insertPostIt(postIt: PostIt){
        database.DAO().insertPostIt(postIt)
    }


}