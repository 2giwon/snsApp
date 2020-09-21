package com.egiwon.snsapp.detail.card

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.egiwon.snsapp.BR
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseAdapter
import com.egiwon.snsapp.base.BaseFragment
import com.egiwon.snsapp.databinding.FragmentCardDetailBinding
import com.egiwon.snsapp.main.MainViewModel
import com.egiwon.snsapp.tab.imagefeed.model.Card
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardDetailFragment : BaseFragment<FragmentCardDetailBinding, CardDetailViewModel>(
    R.layout.fragment_card_detail
) {
    override val viewModel: CardDetailViewModel by viewModels()

    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            vm = viewModel
            sharedvm = sharedViewModel
            rvRecommendCards.adapter = object : BaseAdapter<Card>(
                R.layout.item_home_card,
                BR.item,
                mapOf(BR.sharedvm to sharedViewModel)
            ) {}

            rvRecommendCards.setHasFixedSize(true)
        }

        arguments?.getInt(CARD_ID)?.let {
            viewModel.getCardDetailInfo(it)
        }
    }

    companion object {
        private const val CARD_ID = "card_id"
        private const val IS_LOADED = "is_loaded"

        fun newInstance(id: Int): CardDetailFragment {
            val args = bundleOf(CARD_ID to id)

            val fragment = CardDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}