package com.test.homego.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookmarksDao {
    @Query("SELECT * FROM bookmarks")
    fun getAll(): List<Bookmark>

    @Query("SELECT * FROM bookmarks WHERE advertisementId=(:adId) LIMIT 1")
    fun loadById(adId: Long): Bookmark?

    @Insert
    fun insert(bookmark: Bookmark)

    @Delete
    fun delete(bookmark: Bookmark)
}