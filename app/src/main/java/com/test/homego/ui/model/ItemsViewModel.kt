package com.test.homego.ui.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.test.homego.data.model.Item

class ItemsViewModel : ViewModel() {
    // Used to hold data to recreate Items List on screen rotation
    // Also, used to share Items List with Details and Favorites fragments
    var selected : Item? = null
    var items : List<Item>? = null

    fun select(item: Item) {
        selected = item
    }
}