package com.example.officeemployee.ui.services

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.officeemployee.R
import com.example.officeemployee.databinding.FragmentServicesBinding
import com.google.android.material.tabs.TabLayoutMediator

class ServicesFragment : Fragment(R.layout.fragment_services) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentServicesBinding.bind(view)

        binding.apply {
            val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
            viewPager2.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                when(position) {
                    0 -> tab.text = "Плановые"
                    1 -> tab.text = "Маркет"
                    2 -> tab.text = "Экстренные"
                }
            }.attach()
        }
    }
}