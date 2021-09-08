package com.lfelipe.todolist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lfelipe.todolist.databinding.TodoItemBinding
import com.lfelipe.todolist.model.PostIt

class MainAdapter (
    private val list: List<PostIt>
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(postit: PostIt) = with(itemView) {
            binding.apply{
                tvTitle.text = postit.title
                tvDescription.text = postit.desc
                tvDate.text = postit.date
            }
        }

    }

}