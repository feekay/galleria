package com.lemonade.ph.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lemonade.ph.databinding.ItemTagListBinding

class SearchTagAdapter(
    private val tagList: List<String>,
    private var selectedPosition: Int = 0,
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<TagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder(
            ItemTagListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = tagList.size

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val item = tagList[position]
        holder.bind(item, position == selectedPosition)
        holder.itemView.setOnClickListener {
            updateSelectedItems(position)
            clickListener(item)
        }
    }

    private fun updateSelectedItems(position: Int) {
        val lastSelected = selectedPosition
        selectedPosition = position
        notifyItemChanged(lastSelected)
        notifyItemChanged(position)
    }

}

class TagViewHolder(
    private val binding: ItemTagListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String, isSelected: Boolean) {
        binding.apply {
            tag = item
            selected = isSelected
            executePendingBindings()
        }
    }
}
