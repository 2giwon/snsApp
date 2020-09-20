package com.egiwon.snsapp.tab

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseFragment
import com.egiwon.snsapp.databinding.FragmentTabBinding
import com.egiwon.snsapp.main.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabFragment : BaseFragment<FragmentTabBinding, MainViewModel>(
    R.layout.fragment_tab
) {
    override val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        val tabTitles =
            listOf(getString(R.string.tab_home_title), getString(R.string.tab_image_feed_title))

        with(binding) {
            vpContent.adapter = PagerAdapter(requireActivity())
            TabLayoutMediator(tlContent, vpContent) { tab, position ->
                tab.text = tabTitles[position]
            }.attach()
        }

    }
}