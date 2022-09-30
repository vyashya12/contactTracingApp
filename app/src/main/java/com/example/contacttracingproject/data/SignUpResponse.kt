package com.example.contacttracingproject.data

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("message")
    var message: String,
    @SerializedName("user")
    var user: User
)