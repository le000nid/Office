package com.example.officemanagerapp.ui.tenants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentTenantsListBinding
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.util.hide
import com.example.officemanagerapp.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TenantsFragment: Fragment() {

    private val viewModel: TenantsViewModel by viewModels()
    private lateinit var tenantsAdapter: TenantsAdapter
    private lateinit var binding: FragmentTenantsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tenants_list, container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        fetchingData()
        setUpAdapter()

        return binding.root
    }

    private fun setUpAdapter() {
        tenantsAdapter = TenantsAdapter(TenantsAdapter.OnClickListener {
            // todo(made action onClick)
        })

        binding.tenantsRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = tenantsAdapter
        }
    }

    private fun fetchingData() {
        viewModel.tenantsListLiveData.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Resource.Failure -> {
                    binding.progressBar.hide()
                }
                is Resource.Loading -> { binding.progressBar.show() }
                is Resource.Success -> {
                    binding.tenantsRV.show()
                    binding.progressBar.hide()
                    tenantsAdapter.items = result.value
                }
            }
        }
    }
}