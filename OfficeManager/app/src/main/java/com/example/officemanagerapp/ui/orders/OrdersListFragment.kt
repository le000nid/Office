package com.example.officemanagerapp.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentOrdersListBinding
import com.example.officemanagerapp.models.OrderRVItem
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.util.ORDER_POST_REQUEST
import com.example.officemanagerapp.util.hide
import com.example.officemanagerapp.util.show
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersListFragment : Fragment() {

    private val viewModel: OrdersListViewModel by viewModels()
    private lateinit var ordersAdapter: OrdersListAdapter
    private lateinit var binding: FragmentOrdersListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_orders_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        initListeners()
        setUpAdapter()
        fetchingData()

        return binding.root
    }

    private fun fetchingData() {
        viewModel.ordersListItemsLiveData.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Resource.Failure -> {
                    binding.progressBar.hide()
                }
                is Resource.Loading -> binding.progressBar.show()
                is Resource.Success -> {
                    binding.progressBar.hide()
                    ordersAdapter.items = result.value
                }
            }
        }
    }

    private fun setUpAdapter() {
        ordersAdapter = OrdersListAdapter(OrdersListAdapter.OnClickListener { orderRVItem ->
            when(orderRVItem) {
                is OrderRVItem.Title -> { /* do nothing */}
                is OrderRVItem.Order -> {
                    // todo (add navigation)
                }
            }
        })

        binding.ordersRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = ordersAdapter
        }
    }

    private fun initListeners() {
        setFragmentResultListener(ORDER_POST_REQUEST) { _, _ ->
            Snackbar.make(requireView(), "Заявка на заказ успешно отправлена", Snackbar.LENGTH_SHORT).show()
        }
    }
}