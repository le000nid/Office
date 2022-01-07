package com.example.officemanagerapp.ui.services.planned

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.DialogRateOrderBinding
import com.example.officemanagerapp.databinding.FragmentPlannedBinding
import com.example.officemanagerapp.models.Order
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.ui.services.ServicesFragmentDirections
import com.example.officemanagerapp.ui.services.base.ActiveOrdersAdapter
import com.example.officemanagerapp.ui.services.base.HistoryOrdersAdapter
import com.example.officemanagerapp.ui.services.base.OrderClick
import com.example.officemanagerapp.ui.services.base.ReviewClick
import com.example.officemanagerapp.util.handleApiError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlannedFragment : Fragment() {

    private val viewModel: PlannedViewModel by viewModels()
    private var activeAdapter: ActiveOrdersAdapter? = null
    private var historyAdapter: HistoryOrdersAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlannedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_planned, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        viewModel.getPlannedOrders()

        viewModel.plannedOrders.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {

                }
                is Resource.Loading -> { }
                is Resource.Failure -> handleApiError(it) {  }
            }
        }


        activeAdapter = ActiveOrdersAdapter(OrderClick {
            val action = ServicesFragmentDirections.actionServicesFragmentToOrderDetailedFragment(it.title, it)
            findNavController().navigate(action)
        })

        historyAdapter = HistoryOrdersAdapter(OrderClick {
            val action = ServicesFragmentDirections.actionServicesFragmentToOrderDetailedFragment(it.title, it)
            findNavController().navigate(action)
        }, ReviewClick {
            showCustomInputAlertDialog(it)
        })

        binding.root.findViewById<RecyclerView>(R.id.active_planned_rv).apply {
            layoutManager = object : LinearLayoutManager(context){ override fun canScrollVertically(): Boolean { return false } }
            adapter = activeAdapter
        }

        binding.root.findViewById<RecyclerView>(R.id.history_planned_rv).apply {
            layoutManager = object : LinearLayoutManager(context){ override fun canScrollVertically(): Boolean { return false } }
            adapter = historyAdapter
        }


        binding.apply {
            swipeRefreshPlanned.setOnRefreshListener {
                viewModel?.getPlannedOrders()
                swipeRefreshPlanned.isRefreshing = false
            }
        }

        binding.actionAddOrder.setOnClickListener {
            val action = ServicesFragmentDirections.actionServicesFragmentToPlannedCategoriesFragment()
            findNavController().navigate(action)
        }


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    private fun showCustomInputAlertDialog(order: Order) {
        val dialogBinding = DialogRateOrderBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Оставьте отзыв о заказе")
            .setView(dialogBinding.root)
            .setPositiveButton("Отправить", null)
            .create()
        dialog.setOnShowListener {

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val rating = dialogBinding.ratingBar.rating
                val review = dialogBinding.editTextReview.text.toString()
                val newOrder = order.copy(userRate = rating.toInt(), userReview = review)

                viewModel.putPlannedOrder(newOrder)

                viewModel.plannedPutResponse.observe(viewLifecycleOwner) {
                    // set up progress bar
                    when (it) {
                        is Resource.Success -> {
                            lifecycleScope.launch {
                                viewModel.getPlannedOrders()
                                dialog.dismiss()
                            }
                        }
                        is Resource.Loading -> { }
                        is Resource.Failure -> handleApiError(it) {  }
                    }
                }
            }
        }
        dialog.show()
    }
}
