package com.example.retrofitconnection

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val NUM_TABS = 2
private const val TAB_MOSHI = 0
private const val TAB_GSON = 1

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            TAB_MOSHI -> MoshiFragment()
            TAB_GSON -> GsonFragment()
            else -> MoshiFragment()
        }
    }
}