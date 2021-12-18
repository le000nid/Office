package com.example.officemanagerapp.ui.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.officemanagerapp.R
import com.example.officemanagerapp.database.UserPreferences
import com.example.officemanagerapp.databinding.FragmentLoginBinding
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.responses.AuthUser
import com.example.officemanagerapp.ui.MainActivity

import com.example.officemanagerapp.util.handleApiError
import com.example.officemanagerapp.util.snackbar
import com.example.officemanagerapp.util.startNewActivity
import com.example.officemanagerapp.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var userPreferences: UserPreferences
    private var fcmToken: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.progressbar.visible(false)

        userPreferences = UserPreferences(requireContext())

        userPreferences.fcmToken.asLiveData().observe(viewLifecycleOwner, {
            fcmToken = it
        })

        viewModel.loginResponse.observe(viewLifecycleOwner, {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAccessTokens(it.value.access.token, it.value.refresh.token)
                        requireActivity().startNewActivity(MainActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        })

        binding.btnSignIn.setOnClickListener {
            login()
        }

    }


    private fun login() {
        binding.apply {
            val login = editTextLogin.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (login.isEmpty()) {
                editTextLogin.error = "Логин обязателен!"
                editTextLogin.requestFocus()
                return
            }

            if (password.isEmpty()) {
                editTextPassword.error = "Пароль обязателен!"
                editTextPassword.requestFocus()
                return
            }

            if (fcmToken != null) {
                viewModel.login(AuthUser(login, password, fcmToken!!))
            } else {
                // this should never reach
                requireView().snackbar("Внутренняя ошибка. Попробуйте переустановить приложение")
            }
        }
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}