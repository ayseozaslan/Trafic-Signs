package com.example.trafikk.data.repo

import com.example.trafikk.data.datasource.TrafikTanzimDataSource
import com.example.trafikk.data.entity.BilgiIsaretleri
import com.example.trafikk.data.entity.DurmaVeParketme
import com.example.trafikk.data.entity.TehlikeUyari
import com.example.trafikk.data.entity.TrafikTanzim
import com.example.trafikk.data.entity.Yatayİsaretleme

class TrafikTanzimRepository (var tds:TrafikTanzimDataSource) {

    suspend fun trafiktanzimYukle() : List<TrafikTanzim> = tds.trafiktanzimYukle()

    suspend fun tehlikeuyariYukle():List<TehlikeUyari> =tds.tehlikeuyariYukle()
    suspend fun bilgiisaretleriYukle():List<BilgiIsaretleri> =tds.bilgiisaretleriYukle()
    suspend fun durmaveparketmeYukle():List<DurmaVeParketme> =tds.durmaveparketmeYukle()
    suspend fun yatayisaretlemeYukle():List<Yatayİsaretleme> = tds.yatayisaretlemeYukle()
}