package com.example.nammaskill.ui.map

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nammaskill.databinding.ItemCenterBinding
import com.example.nammaskill.models.TrainingCenter

class CenterAdapter : ListAdapter<TrainingCenter, CenterAdapter.CenterViewHolder>(CenterDiffCallback) {

    class CenterViewHolder(private val binding: ItemCenterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(center: TrainingCenter) {
            binding.textViewCenterName.text = center.name
            binding.textViewCenterLocation.text = center.location
            binding.textViewCenterContact.text = "Contact: ${center.contact}"
            
            // Vision Requirement: Locate the nearest center
            binding.buttonLocate.setOnClickListener {
                val gmmIntentUri = Uri.parse("geo:0,0?q=${center.name}, ${center.location}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                try {
                    it.context.startActivity(mapIntent)
                } catch (e: Exception) {
                    // Fallback if Google Maps is not installed
                    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=${center.name},${center.location}"))
                    it.context.startActivity(webIntent)
                }
            }

            // Vision Requirement: Call back from trainer / Direct contact
            binding.buttonCall.setOnClickListener {
                val dialIntent = Intent(Intent.ACTION_DIAL)
                // Clean the phone number string to keep only digits
                val number = center.contact.filter { it.isDigit() }
                dialIntent.data = Uri.parse("tel:$number")
                it.context.startActivity(dialIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CenterViewHolder {
        val binding = ItemCenterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CenterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CenterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object CenterDiffCallback : DiffUtil.ItemCallback<TrainingCenter>() {
    override fun areItemsTheSame(oldItem: TrainingCenter, newItem: TrainingCenter): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: TrainingCenter, newItem: TrainingCenter): Boolean = oldItem == newItem
}
