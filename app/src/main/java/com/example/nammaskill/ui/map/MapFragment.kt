package com.example.nammaskill.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nammaskill.databinding.FragmentMapBinding
import com.example.nammaskill.models.TrainingCenter

class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CenterAdapter()
        binding.recyclerViewCenters.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCenters.adapter = adapter

        adapter.submitList(getDummyCenters())
    }

    private fun getDummyCenters(): List<TrainingCenter> {
        return listOf(
            TrainingCenter("1", "Government ITI Mysuru", "Vidyaranyapuram, Mysuru", "0821-2481234"),
            TrainingCenter("2", "Koushalya Kendra Mandya", "Near DC Office, Mandya", "08232-220011"),
            TrainingCenter("3", "GTTC Bengaluru", "Rajajinagar, Bengaluru", "080-23112233"),
            TrainingCenter("4", "Rural Skill Center Hassan", "Industrial Area, Hassan", "08172-266554")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
