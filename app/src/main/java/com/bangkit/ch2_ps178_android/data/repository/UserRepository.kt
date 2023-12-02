package com.bangkit.ch2_ps178_android.data.repository

import com.bangkit.ch2_ps178_android.data.model.UserModel
import com.bangkit.ch2_ps178_android.data.preference.UserPreference
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(private val userPreference: UserPreference) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference)
            }.also { instance = it }
    }
}