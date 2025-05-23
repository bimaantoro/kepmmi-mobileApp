package com.example.kepmmiapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kepmmiapp.data.Result
import com.example.kepmmiapp.data.remote.response.UserResponseItem
import com.example.kepmmiapp.data.repository.KepmmiRepository
import com.example.kepmmiapp.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: KepmmiRepository) : ViewModel() {
    private val _profileResult = MutableLiveData<Result<UserResponseItem>>()
    val profileResult: LiveData<Result<UserResponseItem>> = _profileResult

    private val _updateProfileResult = MutableLiveData<Result<UserResponseItem>>()
    val updateProfileResult: LiveData<Result<UserResponseItem>> = _updateProfileResult

    init {
        getProfile()
    }

    private fun getProfile() {
        viewModelScope.launch {
            repository.getProfile().collect {
                _profileResult.value = it
            }
        }
    }

    fun updateProfile(profileData: Map<String, String>) {
        viewModelScope.launch {
            repository.updateProfile(profileData).collect {
                _updateProfileResult.value = it

                if (it is Result.Success) {
                    getProfile()
                }
            }
        }
    }

    fun resetUpdateProfileResult() {
        _updateProfileResult.value = null
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

}