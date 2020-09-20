package com.egiwon.snsapp.tab.imagefeed

import androidx.fragment.app.viewModels
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseFragment
import com.egiwon.snsapp.databinding.FragmentImageFeedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFeedFragment : BaseFragment<FragmentImageFeedBinding, ImageFeedViewModel>(
    R.layout.fragment_image_feed
) {

    override val viewModel: ImageFeedViewModel by viewModels()

}