package com.test.homego.data.model

import com.google.gson.annotations.SerializedName

class AdsData(
    @field:SerializedName("resultCount") var resultCount: Int?,
    @field:SerializedName("start") var start: Int?,
    @field:SerializedName("page") var page: Int?,
    @field:SerializedName("pageCount") var pageCount: Int?,
    @field:SerializedName("itemsPerPage") var itemsPerPage: Int?,
    @field:SerializedName("hasNextPage") var hasNextPage: Boolean?,
    @field:SerializedName("hasPreviousPage") var hasPreviousPage: Boolean?,
    @field:SerializedName("items") var items: List<Item>?
)