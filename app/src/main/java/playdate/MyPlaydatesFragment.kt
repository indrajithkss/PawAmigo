package com.app.pawamigo.ui.playdate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.pawamigo.databinding.FragmentMyPlaydatesBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.app.pawamigo.ui.playdate.Playdate


class MyPlaydatesFragment : Fragment() {

    private lateinit var binding: FragmentMyPlaydatesBinding
    private val firestore = FirebaseFirestore.getInstance()
    private val playdatesList = ArrayList<Playdate>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyPlaydatesBinding.inflate(inflater, container, false)

        binding.rvPlaydates.layoutManager = LinearLayoutManager(requireContext())

        loadPlaydates()

        return binding.root
    }

    private fun loadPlaydates() {
        firestore.collection("playdates")
            .get()
            .addOnSuccessListener { result ->
                playdatesList.clear()
                for (doc in result) {
                    val playdate = doc.toObject(Playdate::class.java)
                    playdatesList.add(playdate)
                }

                binding.rvPlaydates.adapter = PlaydateAdapter(playdatesList)
            }
            .addOnFailureListener {
                // Handle error
            }
    }
}
