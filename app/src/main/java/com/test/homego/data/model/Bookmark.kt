package com.test.homego.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
class Bookmark(
    @PrimaryKey val advertisementId: Long?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "street") val street: String?,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "currency") val currency: String?,
    @ColumnInfo(name = "price") val price: Int?,
    @ColumnInfo(name = "mainPicture") val mainPicture: String?
)