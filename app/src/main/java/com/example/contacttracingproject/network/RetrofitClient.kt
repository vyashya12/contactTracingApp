package com.example.retrofitcovidstatistics.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://disease.sh"

class RetrofitClient {
    companion object {
        private var instance: RetrofitClient? = null
        private var api: RetrofitApi? = null
        @Synchronized
        fun getInstance(): RetrofitClient? {
            if(instance == null) {
                instance = RetrofitClient()
                // build the instance using builder
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                api = retrofit.create(RetrofitApi::class.java)
            }
            return instance
        }
    }

    fun getApi(): RetrofitApi? {
        return api
    }
}