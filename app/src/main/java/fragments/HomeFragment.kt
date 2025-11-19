package com.app.pawamigo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.pawamigo.R
import com.app.pawamigo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 🩵 Random tips
        val tips = listOf(
            "Groom your pet regularly to reduce shedding ✂️",
            "Keep water bowls filled 💧",
            "Reward good behavior with treats 🍪",
            "Playtime keeps your pet happy 🎾"
        )
        binding.tvTip.text = tips.random()

        // 🐾 Load local image based on demo pet type (for now)
        val petType = "Dog"  // You can replace this with value fetched from Firestore later

        val localImageRes = when (petType.lowercase()) {
            "dog" -> R.drawable.ic_dog_default
            "cat" -> R.drawable.ic_cat_default
            else -> R.drawable.ic_pet_placeholder
        }

        binding.imgPet.setImageResource(localImageRes)

        // Pet name and breed placeholder (static for demo)
        binding.tvPetName.text = "Buddy"
        binding.tvPetBreed.text = "Golden Retriever • 3 yrs"
        binding.tvHealth.text = "Healthy & Active 💪"

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
