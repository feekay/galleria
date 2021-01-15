package com.lemonade.ph.views.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lemonade.ph.databinding.RowGalleryPhotoBinding
import com.lemonade.ph.models.GalleryPhoto

class GalleryAdapter(val clickListener: (GalleryPhoto) -> Unit) :
    PagingDataAdapter<GalleryPhoto, GalleryViewHolder>(GalleryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            RowGalleryPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            holder.bind(photo)
            holder.itemView.setOnClickListener {
                clickListener(photo)
            }
        }
    }

}

class GalleryViewHolder(
    private val binding: RowGalleryPhotoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GalleryPhoto) {
        binding.apply {
            photo = item
            executePendingBindings()
        }
    }
}


private class GalleryDiffCallback : DiffUtil.ItemCallback<GalleryPhoto>() {
    override fun areItemsTheSame(oldItem: GalleryPhoto, newItem: GalleryPhoto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GalleryPhoto, newItem: GalleryPhoto): Boolean {
        return oldItem == newItem
    }
}
