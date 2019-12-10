package com.adrian.bucayan.ui.viewmodels.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val authenticationViewModel: MainViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
          return authenticationViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}