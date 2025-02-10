package com.example.trafikk.data.datasource

import com.example.trafikk.data.entity.BilgiIsaretleri
import com.example.trafikk.data.entity.DurmaVeParketme
import com.example.trafikk.data.entity.TehlikeUyari
import com.example.trafikk.data.entity.TrafikTanzim
import com.example.trafikk.data.entity.Yatayİsaretleme
import com.example.trafikk.retrofit.TrafikTanzimDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrafikTanzimDataSource(var tdao: TrafikTanzimDao) {

    suspend fun trafiktanzimYukle() : List<TrafikTanzim> =
        withContext(Dispatchers.IO){
            return@withContext tdao.trafiktanzimYukle().trafiktanzim
        }
    suspend fun tehlikeuyariYukle() : List<TehlikeUyari> =
        withContext(Dispatchers.IO){
            return@withContext tdao.tehlikeuyariYukle().tehlikeuyari
        }
    suspend fun bilgiisaretleriYukle() : List<BilgiIsaretleri> =
        withContext(Dispatchers.IO){
            return@withContext tdao.bilgiisaretleriYukle().bilgiisaretleri
        }
    suspend fun durmaveparketmeYukle() : List<DurmaVeParketme> =
        withContext(Dispatchers.IO){
            return@withContext tdao.durmaveparketmeYukle().durmaveparketme
        }
    suspend fun yatayisaretlemeYukle(): List<Yatayİsaretleme> =
        withContext(Dispatchers.IO){
            return@withContext tdao.yatayisaretlemeYukle().yatayisaretleme
        }
}