package com.egiwon.snsapp.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.egiwon.snsapp.base.BaseAdapter
import com.egiwon.snsapp.base.BasePagedAdapter
import com.egiwon.snsapp.tab.imagefeed.model.Card

@Suppress("UNCHECKED_CAST")
@BindingAdapter("bind:replacePagedItems")
fun RecyclerView.replacePagedItems(items: PagedList<Card>?) {
    if (items != null) {
        (adapter as? BasePagedAdapter<Card>)?.run {
            this.replacePagedItems(items)
        }
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("bind:replaceItem")
fun RecyclerView.replaceItem(items: List<Any>?) {
    if (items != null) {
        (adapter as? BaseAdapter<Any>)?.run {
            replaceItems(items)
            notifyDataSetChanged()
        }
    }
}

@BindingAdapter("bind:loadImageUrl")
fun ImageView.loadImageUrlByGlide(imageUrl: String?) {
    if (imageUrl != null) {

        Glide.with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }

}

