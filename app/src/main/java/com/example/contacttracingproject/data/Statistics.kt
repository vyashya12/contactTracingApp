package com.example.contacttracingproject.data

import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("cases")
    val cases: String,

    @SerializedName("deaths")
    val deaths: String,

    @SerializedName("recovered")
    val recovered: String
)
