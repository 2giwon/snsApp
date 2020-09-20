package com.egiwon.snsapp.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.egiwon.snsapp.R
import com.egiwon.snsapp.databinding.FragmentTabBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabFragment : Fragment() {

    private lateinit var binding: FragmentTabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

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