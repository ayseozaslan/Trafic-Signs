package com.example.trafikk.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CevapTrafikTanzim(var trafiktanzim: List<TrafikTanzim> ,
                             var success:Int)  {
}
//trafik tanzim adında bir liste ve içinde trafik tanzim olacak cevap olarak d asuccess olarak ınt turunde cevap döndürecek