package com.example.trafikk.ui.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trafikk.data.entity.Yatayİsaretleme
import com.example.trafikk.data.repo.TrafikTanzimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YatayIsaretlemeViewModel @Inject constructor (var trepo:TrafikTanzimRepository): ViewModel() {
     var yatayisaretlemeListesi=MutableLiveData<List<Yatayİsaretleme>>()

    init {
        yatayisaretlemeYukle()
    }
    fun yatayisaretlemeYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            yatayisaretlemeListesi.value=trepo.yatayisaretlemeYukle()
        }

    }

}