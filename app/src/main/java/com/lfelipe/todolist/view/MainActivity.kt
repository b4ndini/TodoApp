package com.lfelipe.todolist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.lfelipe.todolist.database.PostItDatabase
import com.lfelipe.todolist.databinding.ActivityMainBinding
import com.lfelipe.todolist.view.adapter.MainAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: PostItDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setDatabase()

        binding.fabAddPostIt.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setDatabase() {
        database = PostItDatabase.getInstance(this)
        getPostIts()

    }

    private fun getPostIts() {



            GlobalScope.launch {
                val list = database.DAO().getPostIts()
                binding.rvPostItList.apply {
                    layoutManager = GridLayoutManager(this@MainActivity, 2)
                    adapter = MainAdapter(list)

                }

            }



    }

}