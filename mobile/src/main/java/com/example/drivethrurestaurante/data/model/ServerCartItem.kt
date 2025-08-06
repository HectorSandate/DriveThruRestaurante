package com.example.drivethrurestaurante.data.model

import com.google.gson.annotations.SerializedName

data class ServerCartItem(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int,
    @SerializedName("comments")
    val specialInstructions: String = ""
) 