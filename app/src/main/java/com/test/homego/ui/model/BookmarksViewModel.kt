package com.test.homego.ui.model

import androidx.lifecycle.ViewModel
import com.test.homego.data.model.Bookmark

class BookmarksViewModel : ViewModel() {
    var bookmarks : List<Bookmark>? = null
}