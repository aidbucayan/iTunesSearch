package com.adrian.bucayan.ui.viewmodels.authentication

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class MyViewModelFactory @Inject constructor(
    private val mainViewModel: MyViewModel) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
      return mainViewModel as T
    }
    throw IllegalArgumentException("Unknown class name")
  }
}