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
import com.example.officemanagerapp.util.setErrorMessage
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
            clearErrors()

            val formErrors = viewModel.getFormErrors()

            if (formErrors.isEmpty()) {
                // todo()
            } else {
                if (formErrors.contains(AddPassViewModel.FormErrors.MISSING_NAME)) {
                    setErrorMessage(binding.layoutName, "ФИО обязателен")
                }
                if (formErrors.contains(AddPassViewModel.FormErrors.MISSING_PHONE)) {
                    setErrorMessage(binding.layoutPhone, "Телефон обязателен")
                }
                if (formErrors.contains(AddPassViewModel.FormErrors.MISSING_DATE_START)) {
                    setErrorMessage(binding.layoutDateStart, "Дата начала обязательна")
                }
                if (formErrors.contains(AddPassViewModel.FormErrors.MISSING_DATE_END)) {
                    setErrorMessage(binding.layoutDateEnd, "Дата окончания обязательна")
                }

                return@setOnClickListener
            }

        }
    }

    private fun clearErrors() {
        binding.layoutName.error = null
        binding.layoutPhone.error = null
        binding.layoutDateStart.error = null
        binding.layoutDateEnd.error = null
    }
}