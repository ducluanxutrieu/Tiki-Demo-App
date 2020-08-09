package com.ducluanxutrieu.tikiapp.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ducluanxutrieu.tikiapp.R;
import com.ducluanxutrieu.tikiapp.data.models.BannerModel;
import com.ducluanxutrieu.tikiapp.databinding.ItemSliderImageBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

import static java.util.Collections.emptyList;

interface BannerListener {
    void bannerClickedListener(String url);
}

public class BannerAdapter extends SliderViewAdapter<BannerAdapter.BannerViewHolder> {
    private final BannerListener bannerListener;
    private List<BannerModel> mListData = emptyList();

    public BannerAdapter(BannerListener bannerListener) {
        this.bannerListener = bannerListener;
    }

    void setData(List<BannerModel> items) {
        mListData = items;
        notifyDataSetChanged();
    }

    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent) {
        ItemSliderImageBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_slider_image, parent, false);
        return new BannerViewHolder(binding, bannerListener);
    }

    @Override
    public void onBindViewHolder(BannerViewHolder holder, int position) {
        holder.bindData(mListData.get(position).getImageUrl(), mListData.get(position).getMobileUrl());
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    public static class BannerViewHolder extends SliderViewAdapter.ViewHolder {
        final ItemSliderImageBinding mBinder;

        final BannerListener listener;

        public BannerViewHolder(ItemSliderImageBinding mBinder, BannerListener listener) {
            super(mBinder.getRoot());
            this.mBinder = mBinder;
            this.listener = listener;
        }

        public void bindData(String url, final String mobileUrl) {
            Glide.with(mBinder.getRoot().getContext()).load(url)
                    .apply(RequestOptions.centerCropTransform().placeholder(R.drawable.tiki_banner)).error(R.drawable.tiki_banner).into(mBinder.ivBanner);
            mBinder.ivBanner.setOnClickListener(view -> listener.bannerClickedListener(mobileUrl));
        }
    }
}
