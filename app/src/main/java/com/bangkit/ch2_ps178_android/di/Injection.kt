package com.bangkit.ch2_ps178_android.di

import android.content.Context
import com.bangkit.ch2_ps178_android.data.preference.UserPreference
import com.bangkit.ch2_ps178_android.data.preference.dataStore
import com.bangkit.ch2_ps178_android.data.repository.UserRepository

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}