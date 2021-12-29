package com.example.officemanagerapp.ui.workers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentProfileBinding
import com.example.officemanagerapp.responses.User
import com.example.officemanagerapp.ui.profile.ProfileViewModel
import com.example.officemanagerapp.util.logout
import com.example.officemanagerapp.util.visible

class WorkersFragment : Fragment(R.layout.fragment_workers) {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}