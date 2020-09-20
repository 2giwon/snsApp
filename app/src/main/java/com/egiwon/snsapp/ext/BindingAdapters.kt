package com.egiwon.snsapp.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egiwon.snsapp.base.BaseAdapter

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
fun ImageView.loadImageUrlByGlide(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .into(this)
}

