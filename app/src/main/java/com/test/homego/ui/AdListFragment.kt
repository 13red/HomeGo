package com.test.homego.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.homego.R
import com.test.homego.adapters.AdsRecyclerViewAdapter

class AdListFragment : Fragment() {

    private var listener: OnAdListFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ad_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = AdsRecyclerViewAdapter(listener)
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAdListFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnAdListFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnAdListFragmentListener {
        fun onListFragmentInteraction()
    }

    companion object {

        @JvmStatic
        fun newInstance() = AdListFragment()
    }
}
