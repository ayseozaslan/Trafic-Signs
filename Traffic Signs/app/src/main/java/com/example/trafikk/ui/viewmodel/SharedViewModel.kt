package com.example.trafikk.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    // Başlangıçta 2 sütunlu görünüm
    val spanCount = MutableLiveData<Int>().apply { value = 2 }
} 