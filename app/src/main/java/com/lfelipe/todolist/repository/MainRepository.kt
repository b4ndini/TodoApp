package com.lfelipe.todolist.repository

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.lfelipe.todolist.database.PostItDatabase
import com.lfelipe.todolist.model.PostIt
import java.lang.Exception
import java.sql.SQLException

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

    suspend fun insertPostIt(postIt: PostIt) : Long{
        return try {
            database.DAO().insertPostIt(postIt)

        }catch (ex: SQLException) {
            return 0
        }catch (ex : SQLiteConstraintException){
            return 0
        }

    }


    suspend fun deletePostIt(postTitle: String) : Int{
        return database.DAO().deletePostIt(postTitle)
    }


}