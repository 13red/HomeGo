package com.test.homego.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.test.homego.R
import com.test.homego.ui.AdListFragment

import kotlinx.android.synthetic.main.list_entry_ad.view.*

class AdsRecyclerViewAdapter(private val mListener: AdListFragment.OnAdListFragmentListener?) :
    RecyclerView.Adapter<AdsRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private val mValues: List<String> = listOf("a", "b", "c")

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as String
            mListener?.onListFragmentInteraction()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_entry_ad, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mAdName.text = item

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mAdName: TextView = mView.adName
    }
}
