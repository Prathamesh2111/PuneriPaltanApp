package com.example.tcprojecttwo.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tcprojecttwo.Adapters.TabLayoutAdapter
import com.example.tcprojecttwo.databinding.FragmentThreeBinding
import com.google.android.material.tabs.TabLayoutMediator

class FragmentThree : Fragment() {

    private  var _binding: FragmentThreeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : TabLayoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TabLayoutAdapter(childFragmentManager, lifecycle)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Raiders"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Defenders"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("All Rounders"))

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Raiders"
                1 -> tab.text = "Defenders"
                2 -> tab.text = "All Rounders"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear binding reference to avoid memory leaks
    }
}