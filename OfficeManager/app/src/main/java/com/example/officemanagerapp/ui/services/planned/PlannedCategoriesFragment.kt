package com.example.officemanagerapp.ui.services.planned

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentPlannedCategoriesBinding
import com.example.officemanagerapp.models.Order
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.util.hide
import com.example.officemanagerapp.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlannedCategoriesFragment : Fragment() {

    private val viewModel: PlannedCategoriesViewModel by viewModels()
    private lateinit var categoriesAdapter: PlannedCategoriesAdapter
    private lateinit var binding: FragmentPlannedCategoriesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planned_categories, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        fetchingData()
        setUpAdapter()

        return binding.root
    }

    private fun setUpAdapter() {
        categoriesAdapter = PlannedCategoriesAdapter(PlannedCategoriesAdapter.OnClickListener {
            Log.i("Navigation", "called")
            val order = Order(categoryId = it.categoryId)
            val action = PlannedCategoriesFragmentDirections.actionPlannedCategoriesFragmentToOrderInfoFragment(it.title, order)
            findNavController().navigate(action)
        })

        binding.categoriesRv.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }

    private fun fetchingData() {
        viewModel.plannedCategories.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Resource.Failure -> {
                    binding.progressBar.hide()
                }
                is Resource.Loading -> { binding.progressBar.show() }
                is Resource.Success -> {
                    binding.categoriesRv.show()
                    binding.progressBar.hide()
                    categoriesAdapter.items = result.value
                }
            }
        }
    }
}