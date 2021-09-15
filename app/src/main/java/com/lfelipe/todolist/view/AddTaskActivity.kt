package com.lfelipe.todolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.lfelipe.todolist.R
import com.lfelipe.todolist.databinding.ActivityAddTaskBinding
import com.lfelipe.todolist.model.PostIt
import com.lfelipe.todolist.viewmodel.AddTaskViewModel

import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTaskActivity : AppCompatActivity() {

    private val viewModel: AddTaskViewModel by viewModel()
    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            newPostIt()
        }
        setObservers()
        setDropDownMenu()

    }

    private fun setObservers() {
        viewModel.postItLiveData.observe(this){
            it?.let{ msg ->
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
                finish()
            }
        }

        viewModel.errorLiveData.observe(this){
            it?.let { errorMsg ->
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
            }
        }
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
    }

}
