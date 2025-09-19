package com.ismoil.lugat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismoil.lugat.databinding.ItemRcBinding
import com.ismoil.lugat.room.entity.Theme
import com.ismoil.lugat.room.entity.Word

class WordAdapter(
    private var list: List<Word>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<WordAdapter.Vh>() {

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

        fun onBind(word: Word) {
            binding.tvName.text = word.word
            binding.tvCount.text = word.id.toString()

            binding.root.setOnClickListener {
                itemClickListener.onItemClick(word)
            }
        }
    }

    // Interface for click events
    interface OnItemClickListener {
        fun onItemClick(word: Word)
    }

    // Method to update the list safely
    fun updateList(newList: List<Word>) {
        list = newList
        notifyDataSetChanged() // For better performance, DiffUtil can be used
    }
}
