package com.lfelipe.todolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lfelipe.todolist.database.PostItDatabase
import com.lfelipe.todolist.databinding.ActivityAddTaskBinding
import com.lfelipe.todolist.model.PostIt
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var database: PostItDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString() ?: "Lorem Ipsum"
            val desc = binding.etDescription.text.toString() ?: "Lorem Ipsum"
            val date = binding.etDescription.text.toString() ?: "01/01/2022"
            val postit = PostIt(title, desc, date)
            database = PostItDatabase.getInstance(this)
            insertPostIt(postit)
        }

    }

    private fun insertPostIt(post: PostIt) {
        GlobalScope.launch {
            database.DAO().insertPostIt(post)
        }
        finish()
    }

}
