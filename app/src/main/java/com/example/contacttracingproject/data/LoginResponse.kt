package com.example.contacttracingproject.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    var message: String,
    @SerializedName("nric")
    var nric: Int
)