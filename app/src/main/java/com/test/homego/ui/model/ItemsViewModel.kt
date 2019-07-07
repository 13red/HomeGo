package com.test.homego.ui.model

import androidx.lifecycle.ViewModel;
import com.test.homego.data.model.Item

class ItemsViewModel : ViewModel() {
    // Used to hold data to recreate Items List on screen rotation
    // Also, used to share Items List with Details and Favorites fragments
    var items : List<Item>? = null
}
