package com.example.nammaskill.ui.apply

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nammaskill.databinding.FragmentApplyBinding
import com.example.nammaskill.models.CourseApplication
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging

class ApplyFragment : Fragment() {

    private var _binding: FragmentApplyBinding? = null
    private val binding get() = _binding!!
    private val args: ApplyFragmentArgs by navArgs()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApplyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSubmit.setOnClickListener {
            submitApplication()
        }
    }

    private fun submitApplication() {
        val name = binding.editTextName.text.toString().trim()
        val phone = binding.editTextPhone.text.toString().trim()
        val location = binding.editTextLocation.text.toString().trim()

        if (name.isEmpty() || phone.isEmpty() || location.isEmpty()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // 1. Create the application object
        val application = CourseApplication(
            name = name,
            phone = phone,
            course_id = args.courseId
        )

        // 2. REALLY Subscribe to notifications for this specific trade
        // If user applies for "Coding", they will now get alerts for "trade_coding"
        val topic = "trade_${args.trade.lowercase().replace(" ", "_")}"
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("ApplyFragment", "Successfully subscribed to $topic")
                }
            }

        // 3. Submit data to Firebase in the background
        db.collection("applications").add(application)
            .addOnSuccessListener {
                Log.d("ApplyFragment", "Application saved to Firestore")
            }
            .addOnFailureListener { e ->
                Log.e("ApplyFragment", "Firestore save failed", e)
            }

        // 4. Instant Confirmation (PRD: Generates Candidate Summary)
        val action = ApplyFragmentDirections.actionApplyToSummary(name, phone, args.courseId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
