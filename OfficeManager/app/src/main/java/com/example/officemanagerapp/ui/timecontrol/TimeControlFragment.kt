package com.example.officemanagerapp.ui.timecontrol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentTimeControlBinding


class TimeControlFragment : Fragment() {

    private lateinit var binding: FragmentTimeControlBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_time_control, container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}