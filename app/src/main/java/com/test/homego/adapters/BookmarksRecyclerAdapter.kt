package com.test.homego.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.homego.R
import com.test.homego.data.PicassoSingleton
import com.test.homego.data.model.Bookmark
import com.test.homego.ui.BookmarksFragment
import kotlinx.android.synthetic.main.list_entry_ad.view.*

class BookmarksRecyclerAdapter(private val mContext: Context,
                               private val mBookmarks : List<Bookmark>,
                               private val mListener: BookmarksFragment.OnBookmarksFragmentListener?)
    : RecyclerView.Adapter<BookmarksRecyclerAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private val mPicasso: Picasso

    init {
        mOnClickListener = View.OnClickListener { v ->
            mListener?.onBookmarkSelected(v.tag as Bookmark)
        }
        mPicasso = PicassoSingleton.getInstance(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_entry_ad, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookmark = mBookmarks[position]

        holder.mAdTitle.text = if (bookmark.title != null) bookmark.title else ""
        holder.mAdLocation.text = if (bookmark.street != null && bookmark.city != null) "${bookmark.street}, ${bookmark.city}" else ""
        holder.mAdPrice.text = if (bookmark.price != null) bookmark.price.toString() else "0"
        holder.mAdCurency.text = if (bookmark.currency != null) bookmark.currency else ""

        bookmark.mainPicture?.let {
                mPicasso.load(it).into(holder.mAdPicture)
        }

        with(holder.mView) {
            tag = bookmark
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mBookmarks.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mAdTitle: TextView = mView.adTitle
        val mAdLocation: TextView = mView.adLocation
        val mAdPrice: TextView = mView.adPrice
        val mAdCurency: TextView = mView.adCurrency
        val mAdPicture: AppCompatImageView = mView.adImage
    }
}