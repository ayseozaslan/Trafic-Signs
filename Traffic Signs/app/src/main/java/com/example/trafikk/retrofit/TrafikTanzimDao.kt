package com.example.trafikk.retrofit

import com.example.trafikk.data.entity.CevapBilgiIsaretleri
import com.example.trafikk.data.entity.CevapDurmaveParketme
import com.example.trafikk.data.entity.CevapTehlikeUyari
import com.example.trafikk.data.entity.CevapTrafikTanzim
import com.example.trafikk.data.entity.CevapYatayIsaretleme
import retrofit2.http.GET

interface TrafikTanzimDao {
    //http://ayseozaslan.com/trafiktanzim/tum_trafiktanzim.php
//http://ayseozaslan.com => base url
// trafiktanzim/tum_trafiktanzim.php => webservis url

    @GET("trafiktanzim/tum_trafiktanzim.php")
    suspend fun trafiktanzimYukle() : CevapTrafikTanzim

    /*
    https://ayseozaslan.com/trafiktanzim/tum_tehlikeuyari.php
     */

    @GET("trafiktanzim/tum_tehlikeuyari.php")
    suspend fun tehlikeuyariYukle():CevapTehlikeUyari

    @GET("trafiktanzim/tum_bilgiisaretleri.php")
    suspend fun bilgiisaretleriYukle():CevapBilgiIsaretleri
    //tum_durmaveparketme.php

   @GET("trafiktanzim/tum_durmaveparketme.php")
    suspend fun durmaveparketmeYukle():CevapDurmaveParketme

    //tum_yatayisaretleme.php

    @GET("trafiktanzim/tum_yatayisaretleme.php")
    suspend fun yatayisaretlemeYukle():CevapYatayIsaretleme

}



