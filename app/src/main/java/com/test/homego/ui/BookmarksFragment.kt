package com.test.homego.ui


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.test.homego.R
import com.test.homego.adapters.BookmarksRecyclerAdapter
import com.test.homego.data.BookmarksDbSingleton
import com.test.homego.data.model.Bookmark
import com.test.homego.ui.model.BookmarksViewModel
import kotlinx.android.synthetic.main.fragment_bookmarks.*
import kotlinx.coroutines.*

class BookmarksFragment : Fragment() {

    private var listener: OnBookmarksFragmentListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            val savedBookmarks = ViewModelProviders.of(activity!!).get(BookmarksViewModel::class.java).bookmarks
            if (savedBookmarks != null) {
                bookmarksProgressBar.visibility = View.GONE
                with(bookmarksList) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = BookmarksRecyclerAdapter(applicationContext, savedBookmarks, listener)
                }
            } else {
                MainScope().launch {
                    val bookmarks = getBookmarks()
                    bookmarksProgressBar.visibility = View.GONE
                    if (bookmarks.isEmpty()) {
                        bookmarksKein.visibility = View.VISIBLE
                    } else {
                        with(bookmarksList) {
                            layoutManager = LinearLayoutManager(context)
                            adapter = BookmarksRecyclerAdapter(applicationContext, bookmarks, listener)
                            ViewModelProviders.of(activity!!).get(BookmarksViewModel::class.java).bookmarks = bookmarks
                        }
                    }
                }
            }
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private suspend fun getBookmarks() : List<Bookmark>  = withContext(Dispatchers.IO) {
        BookmarksDbSingleton.getInstance(context!!).bookmarksDao().getAll()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBookmarksFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnAdsListFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnBookmarksFragmentListener {
        fun onBookmarkSelected(bookmark : Bookmark)
    }

    companion object {
        @JvmStatic
        fun newInstance() = BookmarksFragment()
    }
}
