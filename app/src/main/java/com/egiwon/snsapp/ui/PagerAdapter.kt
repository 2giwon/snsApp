package com.egiwon.snsapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.egiwon.snsapp.ui.tab.home.HomeFragment
import com.egiwon.snsapp.ui.tab.imagefeed.ImageFeedFragment

class PagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    private val fragments = listOf(
        HomeFragment(),
        ImageFeedFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}