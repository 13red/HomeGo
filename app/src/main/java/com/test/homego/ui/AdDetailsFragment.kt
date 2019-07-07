package com.test.homego.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.util.Linkify
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.test.homego.R
import com.test.homego.data.PicassoSingleton
import com.test.homego.data.model.Item
import com.test.homego.ui.model.ItemsViewModel
import kotlinx.android.synthetic.main.ad_details_fragment.*

class AdDetailsFragment : Fragment() {
    private lateinit var viewModel: ItemsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ad_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {
            populateFields(ViewModelProviders.of(activity!!).get(ItemsViewModel::class.java).selected)
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun populateFields(item: Item?) {
        item?.run {
            pictures?.let {
                if (it.size > 0) {
                    PicassoSingleton.getInstance(context!!).load(it[0]).into(itemImage)
                }
            }

            itemPriceUnit.text = if (priceUnit != null) priceUnit else ""
            itemPrice.text = if (price != null) price.toString() else ""
            itemCurrency.text = if (currency != null) currency else ""

            itemTitle.text = if (title != null) title else ""
            itemLocation.text = if (street != null && city != null) "${street}, ${city}" else ""

            itemFloor.text = if (floorLabel != null) floorLabel else ""
            itemSurface.text = if (surfaceLiving != null) surfaceLiving.toString() else ""
            itemRooms.text = if (numberRooms != null) numberRooms.toString() else ""
            itemBalcony.visibility = if (balcony != null) (if (balcony!!) View.VISIBLE else View.GONE) else View.GONE

            itemDescription.text = if (description != null) description else ""

            itemContactPerson.text = if (contactPerson != null) contactPerson else ""
            itemContactPhone.text = if (contactPhone != null) contactPhone else ""
            Linkify.addLinks(itemContactPhone, Linkify.PHONE_NUMBERS);
            itemAgencyPhone.text = if (agencyPhoneDay != null) agencyPhoneDay else ""
            Linkify.addLinks(itemAgencyPhone, Linkify.PHONE_NUMBERS);
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AdDetailsFragment()
    }
}
