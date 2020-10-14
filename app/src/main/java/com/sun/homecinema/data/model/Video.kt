package com.sun.homecinema.data.model

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("key")
    val key: String = "",
    @SerializedName("type")
    val type: String = ""
)
