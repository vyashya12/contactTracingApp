package com.example.contacttracingproject.network

import com.example.contacttracingproject.data.*
import dagger.Module
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitUser {
    @GET("/auth/{nric}")
    fun getUser(@Path("nric") nric: Int): Call<User>

    @PUT("/auth")
    fun editUser(@Body user: User): Call<User>

    @POST("/auth/login")
    fun login(@Body loginModel: LoginModel): Call<LoginResponse>

    @POST("/auth/register")
    fun register(@Body signUpModel: SignUpModel): Call<SignUpResponse>
}