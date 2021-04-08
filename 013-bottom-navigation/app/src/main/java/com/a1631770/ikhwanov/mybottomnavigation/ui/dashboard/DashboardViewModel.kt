package com.a1631770.ikhwanov.mybottomnavigation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is dashboard Fragment"
  }
  val text: LiveData<String> = _text
}