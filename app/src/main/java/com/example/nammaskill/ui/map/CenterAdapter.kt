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
                it.context.startActivity(mapIntent)
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
