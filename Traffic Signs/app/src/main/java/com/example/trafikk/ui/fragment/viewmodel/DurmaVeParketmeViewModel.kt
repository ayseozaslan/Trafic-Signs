package com.example.trafikk.ui.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trafikk.data.entity.DurmaVeParketme
import com.example.trafikk.data.repo.TrafikTanzimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DurmaVeParketmeViewModel @Inject constructor( var trepo:TrafikTanzimRepository):ViewModel(){
    var durmaListesi=MutableLiveData<List<DurmaVeParketme>>()
    init {
        durmaveparketmeYukle()
    }
    fun durmaveparketmeYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            durmaListesi.value=trepo.durmaveparketmeYukle()
        }
    }
}