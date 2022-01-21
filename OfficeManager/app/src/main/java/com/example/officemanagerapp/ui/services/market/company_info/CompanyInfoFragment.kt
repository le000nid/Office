package com.example.officemanagerapp.ui.services.market.company_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentCompanyInfoBinding
import com.example.officemanagerapp.models.Company

class CompanyInfoFragment: Fragment() {

    private lateinit var binding: FragmentCompanyInfoBinding
    private lateinit var infoAdapter: CompanyInfoAdapter
    private val args by navArgs<CompanyInfoFragmentArgs>()
    private lateinit var company: Company

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_company_info, container,false)
        binding.lifecycleOwner = viewLifecycleOwner


        company = args.company
        binding.company = company

        setUpAdapter()

        return binding.root
    }

    private fun setUpAdapter() {
        infoAdapter = CompanyInfoAdapter()

        binding.reviewsRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = infoAdapter
        }

        infoAdapter.items = company.reviewsList ?: listOf()
    }
}