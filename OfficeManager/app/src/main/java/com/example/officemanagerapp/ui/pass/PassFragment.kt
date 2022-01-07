package com.example.officemanagerapp.ui.pass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentPassBinding


class PassFragment : Fragment() {

    private lateinit var binding: FragmentPassBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pass, container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}