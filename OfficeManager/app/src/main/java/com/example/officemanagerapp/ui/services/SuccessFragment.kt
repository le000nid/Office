package com.example.officemanagerapp.ui.services

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentSuccessBinding

class SuccessFragment : Fragment(R.layout.fragment_success) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSuccessBinding.bind(view)

        binding.buttonToHome.setOnClickListener {
            val action = SuccessFragmentDirections.actionSuccessFragmentToHomeFragment()
            findNavController().navigate(action)
        }

        binding.buttonToOrders.setOnClickListener {
            val action = SuccessFragmentDirections.actionSuccessFragmentToServicesFragment()
            findNavController().navigate(action)
        }
    }

    // TODO(Hide somehow back button but in other way)
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}