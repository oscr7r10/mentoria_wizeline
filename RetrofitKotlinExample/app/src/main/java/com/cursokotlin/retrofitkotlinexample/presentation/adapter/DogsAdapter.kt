package com.cursokotlin.retrofitkotlinexample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.retrofitkotlinexample.databinding.ItemDogBinding
import com.cursokotlin.retrofitkotlinexample.fromUrl

class DogsAdapter (private val images: List<String>) : RecyclerView.Adapter<DogsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(private val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(image: String) {
            binding.ivDog.fromUrl(image)
        }
    }
}