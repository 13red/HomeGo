package com.test.homego.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.test.homego.R
import com.test.homego.adapters.PicturesPagerAdapter
import com.test.homego.ui.model.ItemsViewModel
import kotlinx.android.synthetic.main.fragment_pictures.view.*


class PicturesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pictures, container, false)

        activity?.run {
            val item = ViewModelProviders.of(activity!!).get(ItemsViewModel::class.java).selected
            if (item != null) {
                val picturesList = item.pictures
                if (picturesList != null) {
                    val picturesPagerAdapter = PicturesPagerAdapter(view.context, picturesList)
                    val viewPager = view.view_pager
                    viewPager.setAdapter(picturesPagerAdapter)
                }
            }
        } ?: throw IllegalStateException("Activity cannot be null")

        return view
    }


    companion object {
        @JvmStatic
        fun newInstance() = PicturesFragment()
    }
}