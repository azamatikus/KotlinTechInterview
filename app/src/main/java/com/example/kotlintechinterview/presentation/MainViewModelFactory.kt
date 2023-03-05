package com.example.kotlintechinterview.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlintechinterview.data.repository.UserRepositoryImpl
import com.example.kotlintechinterview.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.kotlintechinterview.domain.useCase.GetUserNameUseCase
import com.example.kotlintechinterview.domain.useCase.SaveUserNameUseCase

class MainViewModelFactory (context : Context): ViewModelProvider.Factory {

    private val userRepository by lazy (LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = context))
    }
    private val getUserNameUseCase by lazy (LazyThreadSafetyMode.NONE) {
        GetUserNameUseCase(userRepository)
    }
    private val saveUserNameUseCase by lazy (LazyThreadSafetyMode.NONE) {
        SaveUserNameUseCase(userRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        ) as T
    }
}