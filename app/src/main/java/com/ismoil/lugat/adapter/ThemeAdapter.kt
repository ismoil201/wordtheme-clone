package com.ismoil.lugat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismoil.lugat.databinding.ItemRcBinding
import com.ismoil.lugat.room.entity.Theme

class ThemeAdapter(
    private var list: List<Theme>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ThemeAdapter.Vh>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val binding = ItemRcBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Vh(binding)
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    // ViewHolder class
    inner class Vh(private val binding: ItemRcBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(theme: Theme) {
            binding.tvName.text = theme.name

            binding.root.setOnClickListener {
                itemClickListener.onItemClick(theme)
            }
        }
    }

    // Interface for click events
    interface OnItemClickListener {
        fun onItemClick(theme: Theme)
    }

    // Method to update the list safely
    fun updateList(newList: List<Theme>) {
        list = newList
        notifyDataSetChanged() // For better performance, DiffUtil can be used
    }
}
