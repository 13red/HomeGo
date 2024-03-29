package com.test.homego.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.test.homego.R
import com.test.homego.data.model.Item
import com.test.homego.ui.AdsListFragment

import kotlinx.android.synthetic.main.list_entry_ad.view.*
import com.squareup.picasso.Picasso
import com.test.homego.data.PicassoSingleton

class AdsRecyclerAdapter(private val mContext: Context,
                         private val mItems : List<Item>,
                         private val mListener: AdsListFragment.OnAdsListFragmentListener?)
    : RecyclerView.Adapter<AdsRecyclerAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private val mPicasso: Picasso

    init {
        mOnClickListener = View.OnClickListener { v ->
            mListener?.onItemSelected(v.tag as Item)
        }
        mPicasso = PicassoSingleton.getInstance(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_entry_ad, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems[position]

        holder.mAdTitle.text = if (item.title != null) item.title else ""
        holder.mAdLocation.text = if (item.street != null && item.city != null) "${item.street}, ${item.city}" else ""
        holder.mAdPrice.text = if (item.price != null) item.price.toString() else "0"
        holder.mAdCurency.text = if (item.currency != null) item.currency else ""

        item.pictures?.let {
            if (it.size > 0) {
                mPicasso.load(it[0]).into(holder.mAdPicture)
            }
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mItems.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mAdTitle: TextView = mView.adTitle
        val mAdLocation: TextView = mView.adLocation
        val mAdPrice: TextView = mView.adPrice
        val mAdCurency: TextView = mView.adCurrency
        val mAdPicture: AppCompatImageView = mView.adImage
    }
}
