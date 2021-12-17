package com.example.officeemployee.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.officeemployee.network.Resource
import com.example.officeemployee.repository.UserRepository
import com.example.officeemployee.responses.UserResponse
import com.example.officeemployee.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UserRepository
) : BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val user: LiveData<Resource<UserResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }

}