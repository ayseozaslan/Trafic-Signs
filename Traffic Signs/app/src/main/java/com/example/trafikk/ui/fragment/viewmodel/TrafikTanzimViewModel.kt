package com.example.trafikk.ui.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trafikk.data.entity.TrafikTanzim
import com.example.trafikk.data.repo.TrafikTanzimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrafikTanzimViewModel  @Inject constructor(var trepo:TrafikTanzimRepository) : ViewModel() {

    var trafikListesi = MutableLiveData<List<TrafikTanzim>>()

    init {
        trafiktanzimYukle()
    }

    fun trafiktanzimYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            trafikListesi.value=trepo.trafiktanzimYukle()
        }
    }
}