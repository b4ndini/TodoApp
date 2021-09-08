package com.lfelipe.todolist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.lfelipe.todolist.databinding.ActivityMainBinding
import com.lfelipe.todolist.view.adapter.MainAdapter
import com.lfelipe.todolist.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getPostIts()
        setupObservers()

        binding.fabAddPostIt.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPostIts()

    }

    private fun setupObservers() {
        viewModel.postItLiveData.observe(this) { list ->
            binding.ivEmptyList.visibility = GONE
            list?.let{
                binding.rvPostItList.apply{
                    layoutManager = GridLayoutManager(this@MainActivity, 2)
                    adapter = MainAdapter(list)
                }
            }
        }

        viewModel.errorLiveData.observe(this){
            binding.ivEmptyList.visibility = VISIBLE
        }

    }


}