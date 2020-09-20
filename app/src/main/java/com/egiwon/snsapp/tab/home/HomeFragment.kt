package com.egiwon.snsapp.tab.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.egiwon.snsapp.BR
import com.egiwon.snsapp.EventObserver
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseAdapter
import com.egiwon.snsapp.base.BaseFragment
import com.egiwon.snsapp.databinding.FragmentHomeBinding
import com.egiwon.snsapp.main.MainViewModel
import com.egiwon.snsapp.tab.home.model.User
import com.egiwon.snsapp.tab.imagefeed.model.Card
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home
) {

    override val viewModel: HomeViewModel by viewModels()

    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            vm = viewModel
            initAdapter()
        }

        viewModel.getHomeContents()
        setupObserve()
    }

    override fun setupObserve() {
        super.setupObserve()
        viewModel.selectedItem.observe(viewLifecycleOwner, EventObserver {
            sharedViewModel.setSelectedItem(it)
        })
    }

    private fun FragmentHomeBinding.initAdapter() {
        rvPopularCards.adapter = object : BaseAdapter<Card>(
            R.layout.item_home_card,
            BR.item,
            mapOf(BR.vm to viewModel)
        ) {}

        rvPopularCards.setHasFixedSize(true)

        rvPopularUsers.adapter = object : BaseAdapter<User>(
            R.layout.item_home_user,
            BR.user,
            mapOf(BR.vm to viewModel)
        ) {}

        rvPopularUsers.setHasFixedSize(true)
    }
}