package com.test.homego.adapters

import android.content.Context
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import com.test.homego.R
import com.test.homego.data.PicassoSingleton
import kotlinx.android.synthetic.main.single_picture.view.*

class PicturesPagerAdapter(
    private val context : Context,
    private val pictures : List<String>) : PagerAdapter() {


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return pictures.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.single_picture, container, false) as ViewGroup
        PicassoSingleton.getInstance(context)
            .load(pictures[position])
            .placeholder(R.drawable.download)
            .into(view.itemPicture)
        view.itemPictureIndex.text = "${position + 1} / ${count}"

        container.addView(view)
        return view
    }
}