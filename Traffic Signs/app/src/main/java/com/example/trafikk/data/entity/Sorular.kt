package com.example.trafikk.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Sorular(
    @SerializedName("soru_id")
    var soru_id: Int,
    
    @SerializedName("soru_ad")
    var soru_ad: String,
    
    @SerializedName("soru_resim")
    var soru_resim: String
) : Serializable


/*
data class Sorular(var soru_id:Int,var soru_ad:String,var soru_resim:String) {
}
 */