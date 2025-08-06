package com.example.drivethrurestaurante.data.model

import com.google.gson.annotations.SerializedName

data class MobileCartItem(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int,
    @SerializedName("specialInstructions")
    val comments: String = ""
) 