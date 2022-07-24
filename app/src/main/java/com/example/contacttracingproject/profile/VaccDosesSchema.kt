package com.example.contacttracingproject

import androidx.annotation.DrawableRes
import java.sql.Time
import java.util.*
// Schema for the Vaccine Certificate data
data class VaccDoses(
    var id: Int,
    var date: String,
    var time: String,
    var brand: String,
    var vacc_centre: String
)
