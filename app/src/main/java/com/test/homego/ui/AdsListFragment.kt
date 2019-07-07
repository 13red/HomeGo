package com.test.homego.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.homego.R
import com.test.homego.adapters.AdsRecyclerViewAdapter
import com.test.homego.data.DataConnection
import com.test.homego.data.model.AdsData
import com.test.homego.data.model.Item
import com.test.homego.ui.model.ItemsViewModel
import kotlinx.android.synthetic.main.fragment_ad_list.listProgressBar
import kotlinx.android.synthetic.main.fragment_ad_list.adsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdsListFragment : Fragment() {

    private var listener: OnAdsListFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ad_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {
            val savedItems = ViewModelProviders.of(activity!!).get(ItemsViewModel::class.java).items
            if (savedItems != null) {
                listProgressBar.visibility = View.GONE
                with(adsList) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = AdsRecyclerViewAdapter(applicationContext, savedItems, listener)
                }
            } else {

                DataConnection(activity as AppCompatActivity).getAds(object : Callback<AdsData> {
                    override fun onFailure(call: Call<AdsData>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<AdsData>, response: Response<AdsData>) {
                        response.body()?.let {
                            listProgressBar.visibility = View.GONE
                            with(adsList) {
                                layoutManager = LinearLayoutManager(context)
                                val items = it.items
                                if (items != null) {
                                    adapter = AdsRecyclerViewAdapter(applicationContext, items, listener)
                                    ViewModelProviders.of(activity!!).get(ItemsViewModel::class.java).items = items
                                }
                            }
                        }
                    }
                })
            }
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAdsListFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnAdsListFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnAdsListFragmentListener {
        fun onItemSelected(item : Item)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AdsListFragment()
    }
}
