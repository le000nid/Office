package com.example.officemanagerapp.ui.services.market.companies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentCompaniesListBinding
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.util.hide
import com.example.officemanagerapp.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompaniesListFragment : Fragment() {

    private val viewModel: CompaniesListViewModel by viewModels()
    private lateinit var companiesListAdapter: CompaniesListAdapter
    private lateinit var binding: FragmentCompaniesListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_companies_list, container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        fetchingData()
        setUpAdapter()

        return binding.root
    }

    private fun setUpAdapter() {
        companiesListAdapter = CompaniesListAdapter(CompaniesListAdapter.OnClickListener {
            // todo(made action onClick)
        })

        binding.companiesRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = companiesListAdapter
        }
    }

    private fun fetchingData() {
        viewModel.companiesListLiveData.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Resource.Failure -> {
                    binding.progressBar.hide()
                }
                is Resource.Loading -> { binding.progressBar.show() }
                is Resource.Success -> {
                    binding.companiesRV.show()
                    binding.progressBar.hide()
                    companiesListAdapter.items = result.value
                }
            }
        }
    }
}