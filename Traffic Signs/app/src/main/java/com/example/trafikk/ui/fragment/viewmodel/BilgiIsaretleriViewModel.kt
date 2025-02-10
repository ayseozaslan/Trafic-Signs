package com.example.trafikk.ui.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trafikk.data.entity.BilgiIsaretleri
import com.example.trafikk.data.repo.TrafikTanzimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BilgiIsaretleriViewModel @Inject constructor(var trepo:TrafikTanzimRepository):ViewModel() {

    var isaretlerListesi=MutableLiveData<List<BilgiIsaretleri>>()

    init {
        bilgiisaretleriYukle()
    }

    fun bilgiisaretleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            isaretlerListesi.value=trepo.bilgiisaretleriYukle()
        }
    }

}
