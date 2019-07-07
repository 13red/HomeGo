package com.test.homego.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.homego.R
import com.test.homego.adapters.AdsRecyclerViewAdapter
import com.test.homego.data.DataConnection
import com.test.homego.data.model.AdsData
import kotlinx.android.synthetic.main.fragment_ad_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdsListFragment : Fragment() {

    private var listener: OnAdListFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ad_list, container, false)

        DataConnection(activity as AppCompatActivity).getAds(object : Callback<AdsData> {
            override fun onFailure(call: Call<AdsData>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<AdsData>, response: Response<AdsData>) {
                response.body()?.let {
                    view.progressBar.visibility = View.GONE
                    with(view.list) {
                        layoutManager = LinearLayoutManager(context)
                        val items = it.items
                        if (items != null) {
                            adapter = AdsRecyclerViewAdapter(context, items, listener)
                        }
                    }
                }
            }

        })

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
        fun newInstance() = AdsListFragment()
    }
}
