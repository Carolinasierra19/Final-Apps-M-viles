package com.example.nuevoproyecto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nuevoproyecto.databinding.ItemNeededBinding

class ItemListAdapter : RecyclerView.Adapter<ItemListViewHolder>() {
    private val itemsNeeded = mutableListOf<String>()

    fun submitList(items: List<String>) {
        itemsNeeded.clear()
        itemsNeeded.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNeededBinding.inflate(inflater, parent, false)
        return ItemListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        holder.bind(itemsNeeded[position])
    }

    override fun getItemCount(): Int {
        return itemsNeeded.size
    }
}

class ItemListViewHolder(private val binding: ItemNeededBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.tvItemNeeded.text = item
    }
}

