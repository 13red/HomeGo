package com.test.homego.data.model

import com.google.gson.annotations.SerializedName

class ExternalUrl(
    @field:SerializedName("url") var url: String?,
    @field:SerializedName("type") var type: String?,
    @field:SerializedName("label") var label: String?
)