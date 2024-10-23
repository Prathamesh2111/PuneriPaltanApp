package com.example.tcprojecttwo.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tcprojecttwo.Adapters.GalleryAdapter
import com.example.tcprojecttwo.Adapters.PuneritvAdapter
import com.example.tcprojecttwo.DataClasses.GalleryData
import com.example.tcprojecttwo.R
import com.example.tcprojecttwo.databinding.FragmentOneBinding
import com.example.tcprojecttwo.databinding.FragmentTwoBinding

class FragmentTwo : Fragment() {

    private lateinit var galleryAdapter: GalleryAdapter
    private  var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTwoBinding.inflate(inflater, container, false)
        val galleryData = listOf(
            GalleryData("1", "Season 5"),
            GalleryData("2", "Season 6"),
            GalleryData("3", "Season 7"),
            GalleryData("4", "Season 8"),
        )
        setupRecyclerView(galleryData)
        return binding.root
    }

    fun setupRecyclerView(galleryData: List<GalleryData>) {
        galleryAdapter = GalleryAdapter(galleryData) { galleryData ->
            val bundle = Bundle().apply {
                putString("GALLERY_ID", galleryData.id)
            }
            val fragment = FragmentTwoSCTwo() // Replace with your fragment class
            fragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit()
        }
        binding.galleryRV.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.galleryRV.adapter = galleryAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear binding reference to avoid memory leaks
    }

}