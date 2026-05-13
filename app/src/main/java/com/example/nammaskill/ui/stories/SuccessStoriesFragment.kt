package com.example.nammaskill.ui.stories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nammaskill.databinding.FragmentSuccessStoriesBinding
import com.example.nammaskill.models.SuccessStory

class SuccessStoriesFragment : Fragment() {

    private var _binding: FragmentSuccessStoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuccessStoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StoryAdapter()
        binding.recyclerViewStories.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewStories.adapter = adapter

        adapter.submitList(getDummyStories())
    }

    private fun getDummyStories(): List<SuccessStory> {
        return listOf(
            SuccessStory("1", "Ramesh Kumar", "Assistant Electrician at BESCOM", "Ramesh was a farmer's son who took the 3-month electrician course. Now he earns 15k/month and supports his family."),
            SuccessStory("2", "Lakshmi M.", "Boutique Owner", "After completing the tailoring course, Lakshmi opened her own small boutique in Mandya. She now employs two other women."),
            SuccessStory("3", "Sunil Gowda", "Service Engineer", "Sunil learned mobile repairing through Namma-Skill. He works in a multi-brand service center in Bengaluru."),
            SuccessStory("4", "Anand", "Furniture Workshop Lead", "Anand joined the 1-year carpentry program. He now leads a team at a local furniture workshop in Hassan.")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
