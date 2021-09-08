package com.lfelipe.todolist.database

import androidx.room.*
import com.lfelipe.todolist.model.PostIt

@Dao
interface PostItDao {

    @Query("SELECT * FROM postits")
    suspend fun getPostIts(): List<PostIt>

    @Delete
    suspend fun deletePostIt(post: PostIt)

    @Insert
    suspend fun insertPostIt(post: PostIt)
}