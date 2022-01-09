package com.example.officemanagerapp.ui.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResultListener
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentOrdersListBinding
import com.example.officemanagerapp.util.ORDER_POST_REQUEST
import com.google.android.material.snackbar.Snackbar


class OrdersListFragment : Fragment() {

    private lateinit var binding: FragmentOrdersListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_orders_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        initListeners()

        return binding.root
    }

    private fun initListeners() {
        setFragmentResultListener(ORDER_POST_REQUEST) { _, _ ->
            Snackbar.make(requireView(), "Заявка на заказ успешно отправлена", Snackbar.LENGTH_SHORT).show()
        }
    }


}