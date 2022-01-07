package com.example.officemanagerapp.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.officemanagerapp.databinding.ItemAlertBinding
import com.example.officemanagerapp.databinding.ItemHomeCardBinding
import com.example.officemanagerapp.databinding.ItemNewsBinding
import com.example.officemanagerapp.databinding.ItemTitleBinding
import com.example.officemanagerapp.models.HomeRVItem

sealed class HomeViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

    var onClickListener: HomeAdapter.OnClickListener? = null

    class TitleViewHolder(private val binding: ItemTitleBinding): HomeViewHolder(binding) {
        fun bind(title: HomeRVItem.Title) {
            binding.title = title
        }
    }

    class AlertViewHolder(private val binding: ItemAlertBinding): HomeViewHolder(binding) {
        fun bind(alert: HomeRVItem.Alert) {
            binding.alert = alert
            binding.alertClick = onClickListener
        }
    }

    class HomeCardViewHolder(private val binding: ItemHomeCardBinding): HomeViewHolder(binding) {
        fun bind(homeCard: HomeRVItem.HomeCard) {
            binding.homeCard = homeCard
            binding.cardClick = onClickListener
        }
    }

    class NewsItemViewHolder(private val binding: ItemNewsBinding): HomeViewHolder(binding) {
        fun bind(newsItem: HomeRVItem.NewsItem) {
            binding.newsItem = newsItem
            binding.newsItemClick = onClickListener
        }
    }
}