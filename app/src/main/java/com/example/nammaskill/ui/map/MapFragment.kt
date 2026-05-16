package com.example.nammaskill.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nammaskill.databinding.FragmentMapBinding
import com.example.nammaskill.models.TrainingCenter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale

class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CenterAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var allCenters = listOf<TrainingCenter>()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            getCurrentLocation()
        } else {
            Toast.makeText(context, "Permission denied. Please enter location manually.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        allCenters = getDummyCenters()
        
        adapter = CenterAdapter()
        binding.recyclerViewCenters.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCenters.adapter = adapter
        adapter.submitList(allCenters)

        // Manual Search Logic
        binding.editTextSearchLocation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterCenters(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // GPS Button Logic
        binding.buttonGPS.setOnClickListener {
            checkLocationPermission()
        }
    }

    private fun checkLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                getCurrentLocation()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun getCurrentLocation() {
        try {
            binding.textViewStatus.text = "Detecting location..."
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val geocoder = Geocoder(requireContext(), Locale.getDefault())
                    // Fix for deprecated getFromLocation on newer Android versions
                    if (android.os.Build.VERSION.SDK_INT >= 33) {
                        geocoder.getFromLocation(location.latitude, location.longitude, 1) { addresses ->
                            val district = addresses.getOrNull(0)?.subAdminArea ?: addresses.getOrNull(0)?.locality ?: ""
                            activity?.runOnUiThread {
                                updateLocationUI(district)
                            }
                        }
                    } else {
                        @Suppress("DEPRECATION")
                        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                        val district = addresses?.getOrNull(0)?.subAdminArea ?: addresses?.getOrNull(0)?.locality ?: ""
                        updateLocationUI(district)
                    }
                } else {
                    binding.textViewStatus.text = "Location not found. Ensure GPS is ON."
                }
            }
        } catch (e: SecurityException) {
            Toast.makeText(context, "Location error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateLocationUI(district: String) {
        if (district.isNotEmpty()) {
            binding.editTextSearchLocation.setText(district)
            binding.textViewStatus.text = "Detected: $district"
            filterCenters(district)
        } else {
            binding.textViewStatus.text = "Could not detect district. Try manual search."
        }
    }

    private fun filterCenters(query: String) {
        val filteredList = if (query.isEmpty()) {
            binding.textViewStatus.text = "Showing all centers"
            allCenters
        } else {
            allCenters.filter { 
                it.location.contains(query, ignoreCase = true) || 
                it.name.contains(query, ignoreCase = true) 
            }
        }
        adapter.submitList(filteredList)
        if (filteredList.isEmpty() && query.isNotEmpty()) {
            binding.textViewStatus.text = "No centers found in '$query'"
        } else if (query.isNotEmpty()) {
            binding.textViewStatus.text = "Found ${filteredList.size} centers near '$query'"
        }
    }

    private fun getDummyCenters(): List<TrainingCenter> {
        return listOf(
            TrainingCenter("1", "Government ITI Mysuru", "Vidyaranyapuram, Mysuru District", "0821-2481234"),
            TrainingCenter("2", "Koushalya Kendra Mandya", "Near DC Office, Mandya District", "08232-220011"),
            TrainingCenter("3", "GTTC Bengaluru", "Rajajinagar, Bengaluru District", "080-23112233"),
            TrainingCenter("4", "Rural Skill Center Hassan", "Industrial Area, Hassan District", "08172-266554"),
            TrainingCenter("5", "Skill India Hub Dharwad", "PB Road, Dharwad District", "0836-2255443")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
