package com.example.officemanagerapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.ItemAlertBinding
import com.example.officemanagerapp.databinding.ItemHomeCardBinding
import com.example.officemanagerapp.databinding.ItemNewsBinding
import com.example.officemanagerapp.databinding.ItemTitleBinding
import com.example.officemanagerapp.models.HomeRVItem

class HomeAdapter(
    private val onClickListener: OnClickListener?
): RecyclerView.Adapter<HomeViewHolder>(){

    var items = listOf<HomeRVItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return when(viewType) {
            R.layout.item_title -> HomeViewHolder.TitleViewHolder(
                ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            R.layout.item_alert -> HomeViewHolder.AlertViewHolder(
                ItemAlertBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            R.layout.item_home_card -> HomeViewHolder.HomeCardViewHolder(
                ItemHomeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            R.layout.item_news -> HomeViewHolder.NewsItemViewHolder(
                ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onClickListener = onClickListener
        when(holder) {
            is HomeViewHolder.AlertViewHolder -> holder.bind(items[position] as HomeRVItem.Alert)
            is HomeViewHolder.HomeCardViewHolder -> holder.bind(items[position] as HomeRVItem.HomeCard)
            is HomeViewHolder.NewsItemViewHolder -> holder.bind(items[position] as HomeRVItem.NewsItem)
            is HomeViewHolder.TitleViewHolder -> holder.bind(items[position] as HomeRVItem.Title)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is HomeRVItem.Alert -> R.layout.item_alert
            is HomeRVItem.HomeCard -> R.layout.item_home_card
            is HomeRVItem.NewsItem -> R.layout.item_news
            is HomeRVItem.Title -> R.layout.item_title
        }
    }

    class OnClickListener(val clickListener: (item: HomeRVItem) -> Unit) {
        fun onClick(item: HomeRVItem) = clickListener(item)
    }
}