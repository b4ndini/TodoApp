package com.lfelipe.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lfelipe.todolist.model.PostIt

    @Database(entities = arrayOf(PostIt::class), version = 1)
    abstract class PostItDatabase : RoomDatabase(){

        abstract fun DAO() : PostItDao

        companion object {
            private var INSTANCE : PostItDatabase? = null

            @Synchronized
            fun getInstance(context : Context) : PostItDatabase {

                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PostItDatabase::class.java,
                        "database.db").build()
                }
                return INSTANCE as PostItDatabase
            }
        }
    }
