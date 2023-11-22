package com.agilizzulhaq.ch2_ps178_android.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilizzulhaq.ch2_ps178_android.data.repository.UserRepository
import com.agilizzulhaq.ch2_ps178_android.data.model.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
}