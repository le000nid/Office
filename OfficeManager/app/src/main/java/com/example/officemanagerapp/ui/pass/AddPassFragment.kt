package com.example.officemanagerapp.ui.pass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentAddPassBinding
import com.example.officemanagerapp.ui.FieldsValidator
import dagger.hilt.android.AndroidEntryPoint

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

        return binding.root
    }

    private fun initListeners() {

        binding.txInputName.addTextChangedListener {
            viewModel.txInputName.value = it.toString()
        }

        binding.txInputPhone.addTextChangedListener {
            viewModel.txInputPhone.value = it.toString()
        }

        binding.txInputDateStart.addTextChangedListener {
            viewModel.txInputDateStart.value = it.toString()
        }

        binding.txInputDateEnd.addTextChangedListener {
            viewModel.txInputDateEnd.value = it.toString()
        }

        binding.fabDone.setOnClickListener {
            if (isFormValid()) {

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

        if (!FieldsValidator.nameValidator(binding.layoutName, viewModel.txInputName.value)) { validationFlag = false }
        if (!FieldsValidator.phoneValidator(binding.layoutPhone, viewModel.txInputPhone.value)) { validationFlag = false }
        if (!FieldsValidator.dateStartValidator(binding.layoutDateStart, viewModel.txInputDateStart.value)) { validationFlag = false }
        if (!FieldsValidator.dateEndValidator(binding.layoutDateEnd, viewModel.txInputDateEnd.value)) { validationFlag = false }

        return validationFlag
    }
}