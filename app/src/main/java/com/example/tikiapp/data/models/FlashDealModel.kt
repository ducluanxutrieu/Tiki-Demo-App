package com.example.tikiapp.data.models

import com.google.gson.annotations.SerializedName

data class FlashDealResponseModel(
    @SerializedName("data") val flashDealList: ArrayList<FlashDealModel>,
    @SerializedName("tabs") val tabs: ArrayList<Tabs>,
    @SerializedName("version") val version: String
)

data class FlashDealModel(

    @SerializedName("status") val status: Int,
    @SerializedName("url") val url: String,
    @SerializedName("tags") val tags: String,
    @SerializedName("discount_percent") val discount_percent: Int,
    @SerializedName("special_price") val special_price: Int,
    @SerializedName("special_from_date") val special_from_date: Int,
    @SerializedName("from_date") val from_date: String,
    @SerializedName("special_to_date") val special_to_date: Int,
    @SerializedName("progress") val progress: Progress,
    @SerializedName("product") val product: Product
)

data class Tabs(

    @SerializedName("query_value") val query_value: Int,
    @SerializedName("from_date") val from_date: String,
    @SerializedName("to_date") val to_date: String,
    @SerializedName("display") val display: String,
    @SerializedName("active") val active: Boolean
)

data class Progress(

    @SerializedName("qty") val qty: Int,
    @SerializedName("qty_ordered") val qty_ordered: Int,
    @SerializedName("qty_remain") val qty_remain: Int,
    @SerializedName("percent") val percent: Double,
    @SerializedName("status") val status: String
)

data class Product(

    @SerializedName("id") val id: Int,
    @SerializedName("sku") val sku: String,
    @SerializedName("name") val name: String,
    @SerializedName("url_path") val url_path: String,
    @SerializedName("price") val price: Int,
    @SerializedName("list_price") val ArrayList_price: Int,
    @SerializedName("discount") val discount: Int,
    @SerializedName("rating_average") val rating_average: Int,
    @SerializedName("review_count") val review_count: Int,
    @SerializedName("order_count") val order_count: Int,
    @SerializedName("thumbnail_url") val thumbnail_url: String,
    @SerializedName("is_visible") val is_visible: Boolean,
    @SerializedName("is_fresh") val is_fresh: Boolean,
    @SerializedName("is_flower") val is_flower: Boolean,
    @SerializedName("is_gift_card") val is_gift_card: Boolean,
    @SerializedName("inventory") val inventory: Inventory,
    @SerializedName("url_attendant_input_form") val url_attendant_input_form: String,
    @SerializedName("master_id") val master_id: Int,
    @SerializedName("seller_product_id") val seller_product_id: Int,
    @SerializedName("price_prefix") val price_prefix: String
)

data class Inventory(

    @SerializedName("product_virtual_type") val product_virtual_type: String,
    @SerializedName("fulfillment_type") val fulfillment_type: String
)