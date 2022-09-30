package com.example.contacttracingproject.data

import com.google.gson.annotations.SerializedName

data class SignUpModel(
    @SerializedName("fullname")
    val fullname : String,
    @SerializedName("nric")
    val nric: String,
    @SerializedName("password")
    val password : String
)