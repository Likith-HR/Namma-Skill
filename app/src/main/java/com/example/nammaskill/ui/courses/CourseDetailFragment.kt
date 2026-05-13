package com.example.nammaskill.ui.courses

import android.os.Bundle
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
            val action = CourseDetailFragmentDirections.actionDetailToApply(courseId)
            findNavController().navigate(action)
        }
    }

    private fun loadCourseDetails(courseId: String) {
        // First try to find in dummy data if not in Firestore (for demo)
        val dummy = getDummyCourses().find { it.course_id == courseId }
        if (dummy != null) {
            displayCourse(dummy)
        }

        db.collection("courses").document(courseId).get()
            .addOnSuccessListener { document ->
                val course = document.toObject(Course::class.java)
                course?.let { displayCourse(it) }
            }
    }

    private fun displayCourse(course: Course) {
        binding.textViewDetailTitle.text = course.title
        binding.textViewDetailTrade.text = "Trade: ${course.trade}"
        binding.textViewDetailDuration.text = "Duration: ${course.duration}"
        binding.textViewDetailLocation.text = "Location: ${course.location}"
        binding.textViewDetailEligibility.text = "Eligibility: ${course.eligibility}"
        binding.textViewDetailDescription.text = course.description
    }

    private fun getDummyCourses(): List<Course> {
        return listOf(
            Course("1", "Electrician Training", "Electrician", "3 Months", "Mysuru", "10th Pass", true, "Comprehensive electrician training covering domestic and industrial wiring."),
            Course("2", "Tailoring & Fashion", "Tailoring", "6 Months", "Mandya", "8th Pass", false, "Learn tailoring, dress making, and basic fashion design principles."),
            Course("3", "Mobile Repairing", "Mobile Repair", "2 Months", "Bengaluru", "10th Pass", true, "Hands-on training for repairing hardware and software of all types of smartphones."),
            Course("4", "Carpentry", "Carpentry", "1 Year", "Hassan", "10th Pass", true, "Professional carpentry course including furniture making and wood carving.")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
