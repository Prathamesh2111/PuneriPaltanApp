package com.example.tcprojecttwo.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tcprojecttwo.FragmentFourFrags.TabFragmentAllRounders
import com.example.tcprojecttwo.FragmentFourFrags.TabFragmentDefenders
import com.example.tcprojecttwo.FragmentFourFrags.TabFragmentRaiders

class TabLayoutAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            TabFragmentRaiders()
        } else if (position == 1) {
            TabFragmentDefenders()
        } else {
            TabFragmentAllRounders()
        }
    }
}