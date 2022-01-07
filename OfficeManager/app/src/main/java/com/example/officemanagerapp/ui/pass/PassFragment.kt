package com.example.officemanagerapp.ui.pass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentPassBinding
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.util.hide
import com.example.officemanagerapp.util.show


class PassFragment : Fragment() {

    private lateinit var binding: FragmentPassBinding
    private val viewModel: PassViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pass, container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        fetchingData()

        return binding.root
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
                    // set adapter
                }
            }
        }
    }
}