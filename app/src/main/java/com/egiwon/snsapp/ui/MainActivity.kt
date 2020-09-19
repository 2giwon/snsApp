package com.egiwon.snsapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseActivity
import com.egiwon.snsapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main
) {
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        val tabTitles =
            listOf(getString(R.string.tab_home_title), getString(R.string.tab_image_feed_title))

        with(binding) {
            vpContent.adapter = PagerAdapter(this@MainActivity)
            TabLayoutMediator(tlContent, vpContent) { tab, position ->
                tab.text = tabTitles[position]
            }.attach()
        }

    }


}