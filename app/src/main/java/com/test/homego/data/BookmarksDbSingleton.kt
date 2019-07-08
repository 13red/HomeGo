package com.test.homego.data

import android.content.Context
import androidx.room.Room
import com.test.homego.data.model.BookmarksDb

object BookmarksDbSingleton {
    fun getInstance(context : Context) : BookmarksDb {
        return Room.databaseBuilder(context, BookmarksDb::class.java, "bookmarks").build()
    }
}