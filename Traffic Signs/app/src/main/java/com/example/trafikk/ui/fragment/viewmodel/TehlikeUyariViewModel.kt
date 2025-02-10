package com.example.trafikk.ui.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trafikk.data.entity.TehlikeUyari
import com.example.trafikk.data.repo.TrafikTanzimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TehlikeUyariViewModel @Inject constructor(var trepo:TrafikTanzimRepository):ViewModel() {

    var uyariListesi=MutableLiveData<List<TehlikeUyari>>()
    init {
        tehlikeuyariYukle()
    }

    fun tehlikeuyariYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            uyariListesi.value=trepo.tehlikeuyariYukle()
        }
    }

}