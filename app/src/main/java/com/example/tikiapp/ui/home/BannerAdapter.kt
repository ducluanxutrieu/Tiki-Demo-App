package com.example.tikiapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tikiapp.R
import com.example.tikiapp.databinding.ItemSliderImageBinding
import com.example.tikiapp.data.models.BannerModel
import com.smarteist.autoimageslider.SliderViewAdapter

class BannerAdapter : SliderViewAdapter<BannerAdapter.BannerViewHolder>() {
    private var mListData = ArrayList<BannerModel>()

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bindData(mListData[position].image_url)
    }

    fun setData(items: ArrayList<BannerModel>) {
        mListData = items
//        if (!mListData.isNullOrEmpty()) mListData.removeAt(0)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): BannerViewHolder {
        val binding = DataBindingUtil.inflate<ItemSliderImageBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_slider_image,
            parent,
            false
        )
        return BannerViewHolder(binding)
    }

    override fun getCount(): Int {
        return mListData.size
    }

    class BannerViewHolder(private val mBinder: ItemSliderImageBinding) :
        SliderViewAdapter.ViewHolder(mBinder.root) {
        fun bindData(url: String) {
            Glide.with(mBinder.root.context).load(url)
                .apply { RequestOptions.centerCropTransform() }.into(mBinder.ivBanner)
        }
    }
}