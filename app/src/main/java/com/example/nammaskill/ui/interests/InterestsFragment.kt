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
import com.google.android.material.chip.Chip
import com.google.firebase.messaging.FirebaseMessaging

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

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref?.getBoolean("interests_selected", false) == true) {
            findNavController().navigate(R.id.action_interests_to_courses)
            return
        }

        binding.buttonContinue.setOnClickListener {
            val selectedTrades = mutableListOf<String>()
            
            // Collect selected interests and subscribe to notification topics
            for (i in 0 until binding.chipGroupInterests.childCount) {
                val chip = binding.chipGroupInterests.getChildAt(i) as Chip
                if (chip.isChecked) {
                    val trade = chip.text.toString().lowercase().replace(" ", "_")
                    selectedTrades.add(trade)
                    // Subscribe to FCM topic for this trade
                    FirebaseMessaging.getInstance().subscribeToTopic("trade_$trade")
                }
            }

            with (sharedPref?.edit()) {
                this?.putBoolean("interests_selected", true)
                this?.putStringSet("user_interests", selectedTrades.toSet())
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
