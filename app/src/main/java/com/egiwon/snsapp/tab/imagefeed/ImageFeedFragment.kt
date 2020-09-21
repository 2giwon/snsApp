package com.egiwon.snsapp.tab.imagefeed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.egiwon.snsapp.BR
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseFragment
import com.egiwon.snsapp.base.BasePagedAdapter
import com.egiwon.snsapp.databinding.FragmentImageFeedBinding
import com.egiwon.snsapp.main.MainViewModel
import com.egiwon.snsapp.tab.imagefeed.model.Card
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFeedFragment : BaseFragment<FragmentImageFeedBinding, ImageFeedViewModel>(
    R.layout.fragment_image_feed
) {

    override val viewModel: ImageFeedViewModel by viewModels()

    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            vm = viewModel
            initAdapter()
        }

        viewModel.getImageFeed()
    }

    private fun FragmentImageFeedBinding.initAdapter() {
        rvImageFeed.run {
            adapter = object : BasePagedAdapter<Card>(
                R.layout.item_card,
                BR.card,
                mapOf(BR.sharedvm to sharedViewModel)
            ) {}
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

    }
}