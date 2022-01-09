package com.example.officemanagerapp.ui.services.market.addmarketorder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.ItemSectionedCategoryCardBinding
import com.example.officemanagerapp.models.Category


class MarketCategoriesSectionedAdapter(val callback: MarketCategoryItemClick) :
    RecyclerView.Adapter<MarketCategoriesSectionedAdapter.ViewHolder>() {

    var categories: List<Category> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val withDataBinding: ItemSectionedCategoryCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ViewHolder.LAYOUT,
            parent,
            false)
        return ViewHolder(withDataBinding)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.category = categories[position]
            it.click = callback
        }
    }

    class ViewHolder(val viewDataBinding: ItemSectionedCategoryCardBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_sectioned_category_card
        }
    }
}