package com.example.mobilecalculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel(){

  private val _toggleStateOfInputVoice= MutableLiveData<Boolean>(false)
 val toggleStateOfInputVoice: LiveData<Boolean> = _toggleStateOfInputVoice

 fun setToggle(isOn: Boolean) {
  _toggleStateOfInputVoice.value = isOn
 }

}