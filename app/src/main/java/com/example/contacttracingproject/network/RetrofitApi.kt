package com.example.retrofitcovidstatistics.network

import com.example.contacttracingproject.data.Statistics
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {
    @GET("/v3/covid-19/all")
    suspend fun getStatistics(): Response<Statistics>  // Method 2
    //fun getStatistics(): Call<Statistics>  Method 1

    @GET("/v3/covid-19/countries/malaysia")
    suspend fun getMalaysia(): Response<Statistics>
}