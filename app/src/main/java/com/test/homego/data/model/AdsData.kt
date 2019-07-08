package com.test.homego.data.model

import com.google.gson.annotations.SerializedName

data class AdsData(
    @field:SerializedName("resultCount") val resultCount: Int?,
    @field:SerializedName("start") val start: Int?,
    @field:SerializedName("page") val page: Int?,
    @field:SerializedName("pageCount") val pageCount: Int?,
    @field:SerializedName("itemsPerPage") val itemsPerPage: Int?,
    @field:SerializedName("hasNextPage") val hasNextPage: Boolean?,
    @field:SerializedName("hasPreviousPage") val hasPreviousPage: Boolean?,
    @field:SerializedName("items") val items: List<Item>?
)