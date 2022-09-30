package com.example.contacttracingproject.data

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("nric")
    val nric : String,
    @SerializedName("password")
    val password: String
)