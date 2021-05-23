package com.gamezface.mobileapp.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamezface.domain.location.entity.Location
import com.gamezface.mobileapp.databinding.ItemLocationBinding
import com.gamezface.mobileapp.extensions.loadImage

class LocationsAdapter(
    private val items: List<Location>?,
    private val listener: (id: Long, imageUrl: String?) -> Unit
) : RecyclerView.Adapter<LocationsAdapter.LocationsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LocationsViewHolder(
        ItemLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        items?.getOrNull(position)?.run {
            holder.bind(this, listener)
        }
    }

    override fun getItemCount(): Int = items.orEmpty().size

    inner class LocationsViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(item: Location, listener: (id: Long, imageUrl: String?) -> Unit) {
            binding.apply {
                root.setOnClickListener { listener.invoke(item.id, item.imageUrl) }
                locationNameTextView.text = item.name
                locationTypeTextView.text = item.type
                locationReviewRatingBar.rating = item.review
                locationReviewValueTextView.text = item.review.toString()
                locationImageView.loadImage(
                    root.context,
                    item.imageUrl
                )
            }
        }
    }
}