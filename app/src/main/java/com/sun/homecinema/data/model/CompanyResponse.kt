package com.sun.homecinema.data.model

import com.google.gson.annotations.SerializedName

data class CompanyResponse(
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("logo_path")
    val logo: String? = null
)
