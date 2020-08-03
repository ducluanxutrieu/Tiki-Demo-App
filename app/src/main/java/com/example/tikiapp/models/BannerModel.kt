package com.example.tikiapp.models

import com.google.gson.annotations.SerializedName

data class BannerResponseModel(

    @SerializedName("data") val bannerList: ArrayList<BannerModel>
)

data class BannerModel(

    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("url") val url: String,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("thumbnail_url") val thumbnail_url: String,
    @SerializedName("mobile_url") val mobile_url: String,
    @SerializedName("ratio") val ratio: Double
)