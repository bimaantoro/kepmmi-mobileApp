package com.example.kepmmiapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponseItem(
    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String
)
