package com.test.homego.data.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Bookmark::class), version = 1)
abstract class BookmarksDb : RoomDatabase() {
    abstract fun bookmarksDao(): BookmarksDao
}