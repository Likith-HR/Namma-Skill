package com.example.nammaskill.ui.interests

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nammaskill.R
import com.example.nammaskill.databinding.FragmentInterestsBinding

class InterestsFragment : Fragment() {

    private var _binding: FragmentInterestsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInterestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Check if user has already selected interests
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref?.getBoolean("interests_selected", false) == true) {
            findNavController().navigate(R.id.action_interests_to_courses)
        }

        binding.buttonContinue.setOnClickListener {
            with (sharedPref?.edit()) {
                this?.putBoolean("interests_selected", true)
                this?.apply()
            }
            findNavController().navigate(R.id.action_interests_to_courses)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
