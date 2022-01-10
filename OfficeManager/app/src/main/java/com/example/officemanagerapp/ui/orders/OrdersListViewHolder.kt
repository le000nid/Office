package com.example.officemanagerapp.ui.orders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.officemanagerapp.databinding.ItemOrderBinding
import com.example.officemanagerapp.databinding.ItemTitleBinding
import com.example.officemanagerapp.models.OrderRVItem

sealed class OrdersListViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

    var onClickListener: OrdersListAdapter.OnClickListener? = null

    class TitleViewHolder(private val binding: ItemTitleBinding): OrdersListViewHolder(binding) {
        fun bind(title: OrderRVItem.Title) {
            binding.title.text = title.title
        }
    }

    class OrderViewHolder(private val binding: ItemOrderBinding): OrdersListViewHolder(binding) {
        fun bind(order: OrderRVItem.Order) {
            binding.order = order
            binding.orderClick = onClickListener
        }
    }
}