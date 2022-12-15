package com.inconceptlabs.task.presentation

import androidx.recyclerview.widget.RecyclerView
import com.inconceptlabs.task.databinding.ListItemBinding
import com.inconceptlabs.task.data.Person

class CustomViewHolder(
    private val binding: ListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(person: Person) {
        binding.apply {
            nameTextView.text = person.name
            ageTextView.text = person.age.toString()
        }
    }
}