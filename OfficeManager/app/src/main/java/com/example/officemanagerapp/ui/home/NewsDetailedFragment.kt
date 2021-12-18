package com.example.officemanagerapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentNewsDetailedBinding
import com.example.officemanagerapp.util.bindImage


class NewsDetailedFragment : Fragment(R.layout.fragment_news_detailed) {

    private val args by navArgs<NewsDetailedFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentNewsDetailedBinding.bind(view)

        val itemNews = args.newsItem

        binding.apply {
            bindImage(imgNewsItem, itemNews.photoUrl)
            txTitleNewsItem.text = itemNews.title
            txDescNewsItem.text = itemNews.description
        }

    }
}