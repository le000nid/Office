package com.example.officemanagerapp.ui.orders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.ItemOrderBinding
import com.example.officemanagerapp.databinding.ItemTitleBinding
import com.example.officemanagerapp.models.OrderRVItem

class OrdersListAdapter(
    private val onClickListener: OnClickListener?
): RecyclerView.Adapter<OrdersListViewHolder>() {

    var items = listOf<OrderRVItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersListViewHolder {
        return when(viewType) {
            R.layout.item_title -> OrdersListViewHolder.TitleViewHolder(
                ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            R.layout.item_order -> OrdersListViewHolder.OrderViewHolder(
                ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: OrdersListViewHolder, position: Int) {
        holder.onClickListener = onClickListener
        when(holder) {
            is OrdersListViewHolder.OrderViewHolder -> holder.bind(items[position] as OrderRVItem.Order)
            is OrdersListViewHolder.TitleViewHolder -> holder.bind(items[position] as OrderRVItem.Title)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is OrderRVItem.Title -> R.layout.item_title
            is OrderRVItem.Order -> R.layout.item_order
        }
    }

    class OnClickListener(val clickListener: (item: OrderRVItem) -> Unit) {
        fun onClick(item: OrderRVItem) = clickListener(item)
    }
}