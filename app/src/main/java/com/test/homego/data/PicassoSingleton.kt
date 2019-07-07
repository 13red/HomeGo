package com.test.homego.data

import android.content.Context
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

object PicassoSingleton {
    fun getInstance(context : Context) : Picasso {
        return Picasso.Builder(context)
            .downloader(OkHttp3Downloader(context))
            .build()
    }
}