package com.example.officemanagerapp.ui.pass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentPassBinding
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.util.PASS_POST_REQUEST
import com.example.officemanagerapp.util.formatDateToString
import com.example.officemanagerapp.util.hide
import com.example.officemanagerapp.util.show
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.properties.Delegates.notNull

@AndroidEntryPoint
class PassFragment : Fragment() {

    private val viewModel: PassViewModel by viewModels()
    private lateinit var passAdapter: PassAdapter
    private lateinit var binding: FragmentPassBinding

    private var date by notNull<String>()
    private var dateLong by notNull<Long>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pass, container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        initVariable()
        initClicks()
        initListeners()
        fetchingData()
        setUpAdapter()

        return binding.root
    }

    private fun initListeners() {
        setFragmentResultListener(PASS_POST_REQUEST) { _, _ ->
            Snackbar.make(requireView(), "Пропуск успешно выписан", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun initClicks() {
        binding.txDate.setOnClickListener {
            openDatePicker()
        }

        binding.fab.setOnClickListener {
            val action = PassFragmentDirections.actionPassFragmentToAddPassFragment()
            findNavController().navigate(action)
        }
    }

    private fun initVariable() {
        date = viewModel.date
        dateLong = viewModel.dateLong

        binding.txDate.text = date
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
                    binding.passRV.show()
                    binding.progressBar.hide()
                    passAdapter.items = result.value
                }
            }
        }
    }

    private fun openDatePicker() {
        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val materialDatePicker: MaterialDatePicker<Long> =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select a date")
                .setSelection(today)
                .build()

        materialDatePicker.addOnPositiveButtonClickListener {
            dateLong = it
            val formattedDate = formatDateToString(it)
            date = formattedDate
            binding.txDate.text = formattedDate

            binding.passRV.hide()
            viewModel.getPasses(Date(it))
        }

        materialDatePicker.show(requireActivity().supportFragmentManager, "DATE_PICKER")
    }
}