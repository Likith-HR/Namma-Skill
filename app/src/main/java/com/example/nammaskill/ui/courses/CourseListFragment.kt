package com.example.nammaskill.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nammaskill.R
import com.example.nammaskill.databinding.FragmentCourseListBinding
import com.example.nammaskill.models.Course
import com.google.firebase.firestore.FirebaseFirestore

class CourseListFragment : Fragment() {

    private var _binding: FragmentCourseListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CourseAdapter
    private val db = FirebaseFirestore.getInstance()
    private var allCourses = listOf<Course>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CourseAdapter { course ->
            val action = CourseListFragmentDirections.actionCoursesToDetail(course.course_id)
            findNavController().navigate(action)
        }

        binding.recyclerViewCourses.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCourses.adapter = adapter

        loadCourses()

        binding.chipGroupFilters.setOnCheckedChangeListener { _, checkedId ->
            filterCourses(checkedId)
        }
    }

    private fun loadCourses() {
        db.collection("courses").addSnapshotListener { value, error ->
            if (error != null) return@addSnapshotListener

            val firestoreCourses = value?.toObjects(Course::class.java) ?: emptyList()

            // Vision Requirement: Retain previous dummy courses AND show Firebase courses
            // We combine the dynamic list from Firebase with the static list
            allCourses = firestoreCourses + getDummyCourses()
            
            // Remove duplicates if same course ID exists in both (prioritize Firebase)
            allCourses = allCourses.distinctBy { it.course_id }

            adapter.submitList(allCourses)
        }
    }

    private fun filterCourses(checkedId: Int) {
        val filteredList = when (checkedId) {
            binding.chipElectrician.id -> allCourses.filter { it.trade.contains("Electrician", ignoreCase = true) }
            binding.chipSewing.id -> allCourses.filter { it.trade.contains("Sewing", ignoreCase = true) || it.trade.contains("Tailoring", ignoreCase = true) }
            binding.chipCoding.id -> allCourses.filter { it.trade.contains("Coding", ignoreCase = true) }
            binding.chipMobileRepair.id -> allCourses.filter { it.trade.contains("Mobile Repair", ignoreCase = true) }
            binding.chipShortTerm.id -> allCourses.filter { it.duration.contains("Month", ignoreCase = true) }
            binding.chipLongTerm.id -> allCourses.filter { it.duration.contains("Year", ignoreCase = true) }
            else -> allCourses
        }
        adapter.submitList(filteredList)
    }

    private fun getDummyCourses(): List<Course> {
        return listOf(
            Course("1", "Electrician Training", "Electrician", "3 Months", "Mysuru District", "10th Pass", true, "Comprehensive electrician training."),
            Course("2", "Modern Sewing & Design", "Sewing", "6 Months", "Mandya District", "8th Pass", false, "Learn modern sewing and fashion design."),
            Course("3", "Basic Coding Skills", "Coding", "4 Months", "Bengaluru", "12th Pass", true, "Introduction to computer programming."),
            Course("4", "Mobile Repairing", "Mobile Repair", "2 Months", "Hassan District", "10th Pass", true, "Fix all types of smartphones.")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
