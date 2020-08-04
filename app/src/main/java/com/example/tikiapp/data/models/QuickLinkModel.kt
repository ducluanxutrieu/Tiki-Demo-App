package com.example.tikiapp.data.models

import com.google.gson.annotations.SerializedName

data class QuickLinkResponseModel(
    @SerializedName("data") val quickLink : ArrayList<ArrayList<QuickLinkModel>>
)

data class QuickLinkModel(
    @SerializedName("title") val title : String,
    @SerializedName("content") val content : String,
    @SerializedName("url") val url : String,
    @SerializedName("authentication") val authentication : Boolean,
    @SerializedName("web_url") val web_url : String,
    @SerializedName("image_url") val image_url : String
)