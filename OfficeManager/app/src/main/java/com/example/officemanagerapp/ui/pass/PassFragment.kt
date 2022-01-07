package com.example.officemanagerapp.ui.pass

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentPassBinding
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.util.hide
import com.example.officemanagerapp.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PassFragment : Fragment() {

    private val viewModel: PassViewModel by viewModels()
    private lateinit var passAdapter: PassAdapter
    private lateinit var binding: FragmentPassBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pass, container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        fetchingData()
        setUpAdapter()

        return binding.root
    }

    private fun setUpAdapter() {
        passAdapter = PassAdapter(PassAdapter.OnClickListener {
            // todo(made action onClick)
        })

        binding.passRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = passAdapter
        }
    }

    private fun fetchingData() {
        viewModel.passListLiveData.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Resource.Failure -> {
                    binding.progressBar.hide()
                }
                is Resource.Loading -> { binding.progressBar.show() }
                is Resource.Success -> {
                    binding.progressBar.hide()
                    passAdapter.items = result.value
                }
            }
        }
    }
}