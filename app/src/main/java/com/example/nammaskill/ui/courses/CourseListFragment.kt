package com.example.nammaskill.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
            
            allCourses = value?.toObjects(Course::class.java) ?: emptyList()
            // If empty, add some dummy data for demo purposes as per PRD
            if (allCourses.isEmpty()) {
                allCourses = getDummyCourses()
            }
            adapter.submitList(allCourses)
        }
    }

    private fun filterCourses(checkedId: Int) {
        val filteredList = when (checkedId) {
            binding.chipElectrician.id -> allCourses.filter { it.trade.contains("Electrician", ignoreCase = true) }
            binding.chipTailoring.id -> allCourses.filter { it.trade.contains("Tailoring", ignoreCase = true) }
            binding.chipMobileRepair.id -> allCourses.filter { it.trade.contains("Mobile Repair", ignoreCase = true) }
            binding.chipShortTerm.id -> allCourses.filter { it.duration.contains("Month", ignoreCase = true) }
            binding.chipLongTerm.id -> allCourses.filter { it.duration.contains("Year", ignoreCase = true) }
            else -> allCourses
        }
        adapter.submitList(filteredList)
    }

    private fun getDummyCourses(): List<Course> {
        return listOf(
            Course("1", "Electrician Training", "Electrician", "3 Months", "Mysuru", "10th Pass", true, "Comprehensive electrician training."),
            Course("2", "Tailoring & Fashion", "Tailoring", "6 Months", "Mandya", "8th Pass", false, "Learn tailoring and dress making."),
            Course("3", "Mobile Repairing", "Mobile Repair", "2 Months", "Bengaluru", "10th Pass", true, "Fix all types of smartphones."),
            Course("4", "Carpentry", "Carpentry", "1 Year", "Hassan", "10th Pass", true, "Professional carpentry course.")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
