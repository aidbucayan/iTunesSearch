package com.adrian.bucayan.ui.viewmodels.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * @author Adrian Bucayan
 */

class MyViewModelFactory @Inject constructor(
    private val mainViewModel: MyViewModel) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
      return mainViewModel as T
    }
    throw IllegalArgumentException("Unknown class name")
  }
}