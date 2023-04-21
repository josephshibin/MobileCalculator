package com.example.mobilecalculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel(){
 //private val _toggleStateOfInputVoice= MutableLiveData<Boolean>()
  var toggleStateOfInputVoice= MutableLiveData<Boolean>()
  //get()=_toggleStateOfInputVoice

}