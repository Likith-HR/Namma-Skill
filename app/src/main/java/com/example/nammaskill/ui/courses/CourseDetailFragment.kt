package com.example.nammaskill.ui.courses

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nammaskill.databinding.FragmentCourseDetailBinding
import com.example.nammaskill.models.Course
import com.google.firebase.firestore.FirebaseFirestore

class CourseDetailFragment : Fragment() {

    private var _binding: FragmentCourseDetailBinding? = null
    private val binding get() = _binding!!
    private val args: CourseDetailFragmentArgs by navArgs()
    private val db = FirebaseFirestore.getInstance()
    private var currentTrade: String = "general"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val courseId = args.courseId
        loadCourseDetails(courseId)

        binding.buttonApply.setOnClickListener {
            val action = CourseDetailFragmentDirections.actionDetailToApply(courseId, currentTrade)
            findNavController().navigate(action)
        }
    }

    private fun loadCourseDetails(courseId: String) {
        // 1. Check in dummy data first (for immediate display)
        val dummy = getDummyCourses().find { it.course_id == courseId }
        if (dummy != null) {
            displayCourse(dummy)
        }

        // 2. Fetch from Firestore by the 'course_id' field (Robust logic for Auto-ID documents)
        db.collection("courses")
            .whereEqualTo("course_id", courseId)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val course = documents.documents[0].toObject(Course::class.java)
                    course?.let { displayCourse(it) }
                } else {
                    // Fallback: Try to fetch by Document ID if field search fails
                    db.collection("courses").document(courseId).get().addOnSuccessListener { doc ->
                        val course = doc.toObject(Course::class.java)
                        course?.let { displayCourse(it) }
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("CourseDetail", "Error fetching course details", e)
            }
    }

    private fun displayCourse(course: Course) {
        currentTrade = course.trade
        binding.textViewDetailTitle.text = course.title
        binding.textViewDetailTrade.text = "Trade: ${course.trade}"
        binding.textViewDetailDuration.text = "Duration: ${course.duration}"
        binding.textViewDetailLocation.text = "Location: ${course.location}"
        binding.textViewDetailEligibility.text = "Eligibility: ${course.eligibility}"
        binding.textViewDetailDescription.text = course.description
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
