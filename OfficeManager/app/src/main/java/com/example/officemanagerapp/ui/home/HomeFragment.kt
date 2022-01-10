package com.example.officemanagerapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentHomeBinding
import com.example.officemanagerapp.models.HomeRVItem
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.util.hide
import com.example.officemanagerapp.util.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        setUpAdapter()
        fetchingData()

        return binding.root
    }

    private fun fetchingData() {
        viewModel.homeListItemsLiveData.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Resource.Failure -> {
                    binding.progressBar.hide()
                }
                is Resource.Loading -> binding.progressBar.show()
                is Resource.Success -> {
                    binding.progressBar.hide()
                    homeAdapter.items = result.value
                }
            }
        }
    }

    private fun setUpAdapter() {
        homeAdapter = HomeAdapter(HomeAdapter.OnClickListener { homeRVItem ->
            when(homeRVItem) {
                is HomeRVItem.Alert -> { }
                is HomeRVItem.HomeCard -> { }
                is HomeRVItem.NewsItem -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToNewsDetailedFragment(homeRVItem)
                    findNavController().navigate(action)
                }
                is HomeRVItem.Title -> { /* do nothing */ }
            }
        })

        binding.homeRV.apply {
            setHasFixedSize(true)

            val manager = GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)

            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when(homeAdapter.items[position]) {
                        is HomeRVItem.Title -> 4
                        is HomeRVItem.Alert -> 4
                        is HomeRVItem.HomeCard -> 1
                        is HomeRVItem.NewsItem -> 4
                    }
                }
            }

            layoutManager = manager
            adapter = homeAdapter
        }
    }
}
