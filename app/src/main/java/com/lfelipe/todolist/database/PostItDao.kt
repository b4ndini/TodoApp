package com.lfelipe.todolist.database

import androidx.room.*
import com.lfelipe.todolist.model.PostIt

@Dao
interface PostItDao {

    @Query("SELECT * FROM postits")
    suspend fun getPostIts(): List<PostIt>

    @Query("DELETE FROM postits WHERE title = :postTitle")
    suspend fun deletePostIt(postTitle: String) : Int

    @Insert
    suspend fun insertPostIt(post: PostIt) : Long
}