package com.lfelipe.todolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lfelipe.todolist.R
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

        setDropDownMenu()

    }

    private fun setDropDownMenu() {
        val items = listOf("Red",  "Green", "Blue", "Black", "White", "Purple", "Pink", "Yellow")
        val adapter = ArrayAdapter(this, R.layout.color_list_item, items)
        binding.actColor.setAdapter(adapter)
    }

    private fun newPostIt() {
        val title = binding.etTitle.text.toString()
        val desc = binding.etDescription.text.toString()
        val date = binding.etDescription.text.toString()
        val color = getColorValue(binding.actColor.text.toString())
        val postit = PostIt(title, desc, date, color)
        insertPostIt(postit)
    }

    private fun getColorValue(color: String?): String? {
        if (!color.isNullOrBlank()) {
            return when (color) {
                "Red" -> {
                    "#FF0000"
                }
                "Green" -> {
                    "#00FF00"
                }
                "Blue" -> {
                    "#0000FF"
                }
                "Black" -> {
                    "#000000"
                }
                "White" -> {
                    "#FFFFFF"
                }
                "Purple" -> {
                    "#800080"
                }
                "Pink" -> {
                    "#FF00FF"
                }
                "Yellow" -> {
                    "#FFFF00"
                }
                else -> {
                    "#FFFFFF"
                }
            }
        } else {
            return null
        }
    }

    private fun insertPostIt(post: PostIt) {
        viewModel.insertPostIt(post)
        Toast.makeText(this, "Post-it adicionado com sucesso", Toast.LENGTH_LONG).show()
        finish()
    }

}
