package com.lfelipe.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "postits"
)
data class PostIt(
    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val desc: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "color")
    val color: String? = null)