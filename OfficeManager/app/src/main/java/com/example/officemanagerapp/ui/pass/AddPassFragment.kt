package com.example.officemanagerapp.ui.pass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentAddPassBinding
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.ui.FieldsValidator
import com.example.officemanagerapp.util.handleApiError
import com.example.officemanagerapp.util.hide
import com.example.officemanagerapp.util.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddPassFragment : Fragment() {

    private lateinit var binding: FragmentAddPassBinding
    private val viewModel: AddPassViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_pass, container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        initListeners()
        observePostPass()

        return binding.root
    }

    private fun observePostPass() {
        viewModel.passResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Success -> {
                    binding.progressBar.hide()
                    findNavController().popBackStack()
                }
                is Resource.Loading -> {
                    binding.allViews.visibility = View.GONE
                    binding.progressBar.show()
                }
                is Resource.Failure -> {
                    binding.allViews.visibility = View.VISIBLE
                    binding.progressBar.hide()
                }
            }
        }
    }

    private fun initListeners() {

        binding.txInputName.addTextChangedListener {
            viewModel.fullName.value = it.toString()
        }

        binding.txInputPhone.addTextChangedListener {
            viewModel.phone.value = it.toString()
        }

        binding.txInputDateStart.addTextChangedListener {
            viewModel.dateStart.value = it.toString()
        }

        binding.txInputDateEnd.addTextChangedListener {
            viewModel.dateEnd.value = it.toString()
        }

        binding.fabDone.setOnClickListener {
            if (isFormValid()) {
                viewModel.postPass()
            } else {
                return@setOnClickListener
            }
        }

    }

    private fun isFormValid(): Boolean {
        var validationFlag = true

        FieldsValidator.clearError(binding.layoutName)
        FieldsValidator.clearError(binding.layoutPhone)
        FieldsValidator.clearError(binding.layoutDateStart)
        FieldsValidator.clearError(binding.layoutDateEnd)

        if (!FieldsValidator.nameValidator(binding.layoutName, viewModel.fullName.value)) { validationFlag = false }
        if (!FieldsValidator.phoneValidator(binding.layoutPhone, viewModel.phone.value)) { validationFlag = false }
        if (!FieldsValidator.dateStartValidator(binding.layoutDateStart, viewModel.dateStart.value)) { validationFlag = false }
        if (!FieldsValidator.dateEndValidator(binding.layoutDateEnd, viewModel.dateEnd.value)) { validationFlag = false }

        return validationFlag
    }
}