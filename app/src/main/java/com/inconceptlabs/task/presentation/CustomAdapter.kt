package com.inconceptlabs.task.presentation

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.inconceptlabs.task.databinding.ListItemBinding
import com.inconceptlabs.task.data.Person
import java.util.ArrayList

class CustomAdapter : RecyclerView.Adapter<CustomViewHolder>() {
    private var items: MutableList<Person> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(items: MutableList<Person>) {
        this.items.clear()
        this.items = items
        notifyDataSetChanged()
    }
}