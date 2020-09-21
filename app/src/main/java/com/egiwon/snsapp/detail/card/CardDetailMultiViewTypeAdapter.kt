package com.egiwon.snsapp.detail.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.snsapp.BR
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseAdapter
import com.egiwon.snsapp.databinding.ItemCardBinding
import com.egiwon.snsapp.databinding.ItemHomeUserBinding
import com.egiwon.snsapp.databinding.ItemRecommendCardBinding
import com.egiwon.snsapp.tab.home.model.User
import com.egiwon.snsapp.tab.imagefeed.model.Card
import kotlin.reflect.KClass

class CardDetailMultiViewTypeAdapter(
    private val mapper: Map<KClass<out Any>, Int> = mapOf()
) : RecyclerView.Adapter<CardDetailMultiViewTypeAdapter.CardDetailViewHolder>() {

    private val items = mutableListOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDetailViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val binding: ViewDataBinding = requireNotNull(DataBindingUtil.bind(view))
        if (viewType == R.layout.item_recommend_card) {
            (binding as? ItemRecommendCardBinding)?.let {
                it.rvRecommendCards.adapter =
                    object : BaseAdapter<Card>(
                        R.layout.item_home_card,
                        BR.item
                    ) {}

                it.rvRecommendCards.setHasFixedSize(true)
            }
        }
        return CardDetailViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CardDetailViewHolder, position: Int) {
        (holder.binding as? ItemCardBinding)?.apply {
            if (items[position] is Card) {
                card = items[position] as Card
            }
        }

        (holder.binding as? ItemHomeUserBinding)?.apply {
            if (items[position] is User) {
                user = items[position] as User
            }
        }

        (holder.binding as? ItemRecommendCardBinding)?.apply {
            if (items[position] is List<*>) {
                @Suppress("UNCHECKED_CAST")
                ((rvRecommendCards.adapter as? BaseAdapter<Card>)?.replaceItems(items[position] as List<Card>))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return requireNotNull(mapper[items[position]::class])
    }

    fun setItems(items: List<Any>?) {
        if (items != null) {
            this.items.clear()
            this.items.addAll(items)
        }
    }

    inner class CardDetailViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)
}