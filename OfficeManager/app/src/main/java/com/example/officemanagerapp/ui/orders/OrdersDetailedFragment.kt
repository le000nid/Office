package com.example.officemanagerapp.ui.orders

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.DialogRateOrderBinding
import com.example.officemanagerapp.databinding.FragmentOrdersDetailedBinding
import com.example.officemanagerapp.models.OrderRVItem
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.util.ORDER_PAID
import com.example.officemanagerapp.util.handleApiError
import com.example.officemanagerapp.util.visible
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersDetailedFragment : Fragment() {

    private val viewModel: OrdersDetailedViewModel by viewModels()
    private val args by navArgs<OrdersDetailedFragmentArgs>()
    private lateinit var binding: FragmentOrdersDetailedBinding
    private lateinit var order: OrderRVItem.Order

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_orders_detailed, container,false)

        order = args.order
        binding.order = order

        updateReviewState()
        updatePaidState()

        initListeners()
        fetchingData()

        // todo(Add color changes)
        // todo(Add update get request when we go back on orders list)
        // todo(fix layout when we type long comment)
        // todo(add rate depending on number)

        return binding.root
    }

    private fun fetchingData() {

        viewModel.reviewPutResponse.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    Snackbar.make(requireView(), "Отзыв успешно отправлен", Snackbar.LENGTH_SHORT).show()
                    viewModel.getOrderItem(1)
                }
                is Resource.Loading -> {
                    binding.allViews.visible(false)
                    binding.btnPay.visible(false)
                    binding.btnReview.visible(false)

                    binding.progressBar.visible(true)
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }

        viewModel.orderItemLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visible(false)

                    order = it.value
                    binding.order = order

                    updateReviewState()
                    updatePaidState()

                    binding.allViews.visible(true)
                }
                is Resource.Loading -> {

                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }
    }

    /**
     * Case 1. We have only rate -> hide btn "Review" and show only rate
     * Case 2. We have rate and review -> hide btn "Review" and show rate and review
     * Case 3. We don't have rate or review -> show btn "Review" and hide rate and review
     */
    private fun updateReviewState() {

        if(order.rate != null) {
            binding.apply {
                reviewViews.visible(true)
                btnReview.visible(false)
            }
        } else {
            binding.apply {
                reviewViews.visible(false)
                btnReview.visible(true)
            }
        }
    }

    private fun updatePaidState() {
        if (order.status == ORDER_PAID) {
            binding.btnPay.visible(false)
        } else {
            binding.btnPay.visible(true)
        }
    }

    private fun initListeners() {
        binding.btnReview.setOnClickListener {
            showCustomInputAlertDialog(args.order)
        }

        binding.btnPay.setOnClickListener {
            val newOrder = order.copy(status = ORDER_PAID)
            order = newOrder
            binding.order = newOrder
            updatePaidState()
        }
    }

    private fun showCustomInputAlertDialog(order: OrderRVItem.Order) {
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
                val newOrder = order.copy(rate = rating, review = review)

                viewModel.putReviewOrder(newOrder)

                dialog.dismiss()
            }
        }
        dialog.show()
    }
}