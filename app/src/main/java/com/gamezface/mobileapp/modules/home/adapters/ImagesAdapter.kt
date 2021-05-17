package com.gamezface.mobileapp.modules.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamezface.mobileapp.core.utils.loadImage
import com.gamezface.mobileapp.databinding.ItemLocationInnerPictureBinding
import com.gamezface.mobileapp.modules.home.models.Image

class ImagesAdapter(
    private val items: List<Image>?
) :
    RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImagesViewHolder(
        ItemLocationInnerPictureBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        items?.getOrNull(position)?.run {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = items?.size ?: 0

    open inner class ImagesViewHolder(private val binding: ItemLocationInnerPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        open fun bind(item: Image) {
            binding.locationImageView.loadImage(binding.root.context, item.downloadUrl)
        }
    }
}