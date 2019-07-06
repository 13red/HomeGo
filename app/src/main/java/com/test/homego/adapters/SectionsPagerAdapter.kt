package com.test.homego.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.test.homego.ui.AdListFragment
import com.test.homego.ui.main.PlaceholderFragment

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position) {
            1 -> {
                return PlaceholderFragment.newInstance(position)
            }
            else -> {
                return AdListFragment.newInstance()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}