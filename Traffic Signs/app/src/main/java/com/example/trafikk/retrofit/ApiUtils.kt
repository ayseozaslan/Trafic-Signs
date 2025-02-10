package com.example.trafikk.retrofit

class ApiUtils {
    companion object{
        //val BASE_URL = "https://ayseozaslan.com/"
 val BASE_URL="https://ayseozaslan.com/"
        fun getTrafikTanzimDao() : TrafikTanzimDao{
            return RetrofitClient.getClient(BASE_URL).create(TrafikTanzimDao::class.java)

        }
    }
}