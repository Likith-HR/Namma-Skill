package com.example.nammaskill.ui.stories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nammaskill.databinding.ItemSuccessStoryBinding
import com.example.nammaskill.models.SuccessStory

class StoryAdapter : ListAdapter<SuccessStory, StoryAdapter.StoryViewHolder>(StoryDiffCallback) {

    class StoryViewHolder(private val binding: ItemSuccessStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: SuccessStory) {
            binding.textViewStoryName.text = story.name
            binding.textViewStoryRole.text = story.role
            binding.textViewStoryDescription.text = story.description
            binding.imageViewStory.setImageResource(story.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemSuccessStoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object StoryDiffCallback : DiffUtil.ItemCallback<SuccessStory>() {
    override fun areItemsTheSame(oldItem: SuccessStory, newItem: SuccessStory): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: SuccessStory, newItem: SuccessStory): Boolean = oldItem == newItem
}
