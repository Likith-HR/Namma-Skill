package com.example.nammaskill.ui.courses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nammaskill.databinding.ItemCourseBinding
import com.example.nammaskill.models.Course

class CourseAdapter(private val onClick: (Course) -> Unit) :
    ListAdapter<Course, CourseAdapter.CourseViewHolder>(CourseDiffCallback) {

    class CourseViewHolder(val binding: ItemCourseBinding, val onClick: (Course) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        private var currentCourse: Course? = null

        init {
            itemView.setOnClickListener {
                currentCourse?.let {
                    onClick(it)
                }
            }
        }

        fun bind(course: Course) {
            currentCourse = course
            binding.textViewTitle.text = course.title
            binding.textViewDuration.text = "Duration: ${course.duration}"
            binding.textViewLocation.text = "Location: ${course.location}"
            binding.chipJobGuarantee.visibility = if (course.job_guarantee) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)
    }
}

object CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.course_id == newItem.course_id
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem == newItem
    }
}
