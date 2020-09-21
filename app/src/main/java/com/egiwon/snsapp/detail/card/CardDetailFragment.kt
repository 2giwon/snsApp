package com.egiwon.snsapp.detail.card

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseFragment
import com.egiwon.snsapp.databinding.FgCardDetailBinding
import com.egiwon.snsapp.detail.model.RecommendCards
import com.egiwon.snsapp.main.MainViewModel
import com.egiwon.snsapp.tab.home.model.User
import com.egiwon.snsapp.tab.imagefeed.model.Card
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardDetailFragment : BaseFragment<FgCardDetailBinding, CardDetailViewModel>(
    R.layout.fg_card_detail
) {
    override val viewModel: CardDetailViewModel by viewModels()

    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            vm = viewModel
            sharedvm = sharedViewModel
            rvViews.adapter = CardDetailMultiViewTypeAdapter(
                sharedViewModel,
                mapOf(
                    Card::class to R.layout.item_card,
                    User::class to R.layout.item_home_user,
                    RecommendCards::class to R.layout.item_recommend_card
                )
            )
            rvViews.setHasFixedSize(true)
            rvViews.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        arguments?.getInt(CARD_ID)?.let {
            viewModel.getCardDetailInfo(it)
        }
    }

    override fun setupObserve() {
        super.setupObserve()

        viewModel.detailItems.observe(this, Observer {
            (binding.rvViews.adapter as? CardDetailMultiViewTypeAdapter)?.setItems(it)
        })
    }

    companion object {
        private const val CARD_ID = "card_id"

        fun newInstance(id: Int): CardDetailFragment {
            val args = bundleOf(CARD_ID to id)

            val fragment = CardDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}