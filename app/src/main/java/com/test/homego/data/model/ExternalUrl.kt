package com.test.homego.data.model

import com.google.gson.annotations.SerializedName

data class ExternalUrl(
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("type") val type: String?,
    @field:SerializedName("label") val label: String?
)