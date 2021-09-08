package com.lfelipe.todolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lfelipe.todolist.database.PostItDatabase
import com.lfelipe.todolist.databinding.ActivityAddTaskBinding
import com.lfelipe.todolist.model.PostIt
import com.lfelipe.todolist.repository.MainRepository
import com.lfelipe.todolist.viewmodel.AddTaskViewModel
import com.lfelipe.todolist.viewmodel.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTaskActivity : AppCompatActivity() {

    private lateinit var viewModel: AddTaskViewModel
    private lateinit var binding: ActivityAddTaskBinding
    val repository: MainRepository by lazy { MainRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)

        binding.btnSave.setOnClickListener {
           newPostIt()
        }

    }

    private fun newPostIt() {
        val title = binding.etTitle.text.toString()
        val desc = binding.etDescription.text.toString()
        val date = binding.etDescription.text.toString()
        val postit = PostIt(title, desc, date)
        insertPostIt(postit)
    }

    private fun insertPostIt(post: PostIt) {
        viewModel.insertPostIt(post)
        Toast.makeText(this, "Post-it adicionado com sucesso", Toast.LENGTH_LONG).show()
        finish()
    }

}
