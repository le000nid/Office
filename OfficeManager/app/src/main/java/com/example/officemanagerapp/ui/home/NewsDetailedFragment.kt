package com.example.officemanagerapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentNewsDetailedBinding


class NewsDetailedFragment : Fragment() {

    private val args by navArgs<NewsDetailedFragmentArgs>()
    private lateinit var binding: FragmentNewsDetailedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_detailed, container,false)

        binding.newsItem = args.newsItem

        return binding.root
    }
}