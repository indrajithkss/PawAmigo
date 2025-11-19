package com.app.pawamigo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.pawamigo.R
import com.app.pawamigo.databinding.FragmentDiscoverBinding

class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)

        setupRecycler()

        return binding.root
    }

    private fun setupRecycler() {
        val ownersList = listOf(
            NearbyOwner(
                ownerName = "John",
                petName = "Buddy",
                petType = "Golden Retriever",
                distance = "1.2 km",
                imageRes = R.drawable.dog1
            ),
            NearbyOwner(
                ownerName = "Meera",
                petName = "Snow",
                petType = "Persian Cat",
                distance = "800 m",
                imageRes = R.drawable.ic_cat_2
            ),
            NearbyOwner(
                ownerName = "Arun",
                petName = "Rocky",
                petType = "Rottweiler",
                distance = "2.4 km",
                imageRes = R.drawable.ic_dog_2
            ),
            NearbyOwner(
                ownerName = "jack",
                petName = "catzy",
                petType = "Doberman",
                distance = "2.4 km",
                imageRes = R.drawable.ic_dog_3
            ),
            NearbyOwner(
                ownerName = "zana",
                petName = "Mickey",
                petType = "Persian",
                distance = "2. km",
                imageRes = R.drawable.ic_cat_1
            ),
            NearbyOwner(
                ownerName = "Max",
                petName = "Jackson",
                petType = "Border Collie",
                distance = "2.4 km",
                imageRes = R.drawable.ic_dog_4
            )
        )

        binding.rvNearbyOwners.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNearbyOwners.adapter = NearbyOwnersAdapter(ownersList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
