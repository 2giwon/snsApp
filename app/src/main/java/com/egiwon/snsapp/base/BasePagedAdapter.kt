package com.egiwon.snsapp.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class BasePagedAdapter<IDENTIFIER : BaseIdentifier>(
    @LayoutRes private val layoutResId: Int,
    private val bindingId: Int,
    private val viewModels: Map<Int?, BaseViewModel> = mapOf()
) : PagedListAdapter<IDENTIFIER, BindingViewHolder>(object : DiffUtil.ItemCallback<IDENTIFIER>() {
    override fun areItemsTheSame(oldItem: IDENTIFIER, newItem: IDENTIFIER): Boolean {
        return oldItem.identifier == newItem.identifier
    }

    override fun areContentsTheSame(oldItem: IDENTIFIER, newItem: IDENTIFIER): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        val binding: ViewDataBinding = requireNotNull(DataBindingUtil.bind(view))
        return BindingViewHolder(binding, bindingId, viewModels)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) =
        holder.onBind(getItem(position))

    fun replacePagedItems(items: PagedList<IDENTIFIER>?) {
        if (items != null) {
            submitList(items)
        }
    }
}