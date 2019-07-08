package com.test.homego.data.model

import com.google.gson.annotations.SerializedName

class Item(
    @field:SerializedName("advertisementId") val advertisementId: Long?,
    @field:SerializedName("score") val score: Float?,
    @field:SerializedName("agencyId") val agencyId: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("street") val street: String?,
    @field:SerializedName("zip") val zip: String?,
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("city") val city: String?,
    @field:SerializedName("country") val country: String?,
    @field:SerializedName("geoLocation") val geoLocation: String?,
    @field:SerializedName("offerType") val offerType: String?,
    @field:SerializedName("objectCategory") val objectCategory: String?,
    @field:SerializedName("objectType") val objectType: Int?,
    @field:SerializedName("numberRooms") val numberRooms: Float?,
    @field:SerializedName("floor") val floor: Int?,
    @field:SerializedName("surfaceLiving") val surfaceLiving: Int?,
    @field:SerializedName("surfaceUsable") val surfaceUsable: Int?,
    @field:SerializedName("currency") val currency: String?,
    @field:SerializedName("sellingPrice") val sellingPrice: Int?,
    @field:SerializedName("price") val price: Int?,
    @field:SerializedName("priceUnit") val priceUnit: String?,
    @field:SerializedName("timestamp") val timestamp: Long?,
    @field:SerializedName("timestampStr") val timestampStr: String?,
    @field:SerializedName("balcony") val balcony: Boolean?,
    @field:SerializedName("lastModified") val lastModified: Long?,
    @field:SerializedName("searchInquiryTimestamp") val searchInquiryTimestamp: Long?,
    @field:SerializedName("picFilename1Small") val picFilename1Small: String?,
    @field:SerializedName("picFilename1Medium") val picFilename1Medium: String?,
    @field:SerializedName("pictures") val pictures: List<String>?,
    @field:SerializedName("objectTypeLabel") val objectTypeLabel: String?,
    @field:SerializedName("countryLabel") val countryLabel: String?,
    @field:SerializedName("floorLabel") val floorLabel: String?,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("listingType") val listingType: String?,
    @field:SerializedName("agencyLogoUrl") val agencyLogoUrl: String?,
    @field:SerializedName("agencyPhoneDay") val agencyPhoneDay: String?,
    @field:SerializedName("contactPerson") val contactPerson: String?,
    @field:SerializedName("contactPhone") val contactPhone: String?,
    @field:SerializedName("interestedFormType") val interestedFormType: Int?,
    @field:SerializedName("externalUrls") val externalUrls: List<ExternalUrl>?,
    @field:SerializedName("picFilename1") val picFilename1: String?,
    @field:SerializedName("lift") val lift: Boolean?,
    @field:SerializedName("wheelchairAccess") val wheelchairAccess: Boolean?,
    @field:SerializedName("animalAllowed") val animalAllowed: Boolean?,
    @field:SerializedName("builtNew") val builtNew: Boolean?,
    @field:SerializedName("adUrl") val adUrl: String?,
    @field:SerializedName("adUrlTitle") val adUrlTitle: String?
)