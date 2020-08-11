package com.ducluanxutrieu.tikiapp.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ducluanxutrieu.tikiapp.R;
import com.ducluanxutrieu.tikiapp.databinding.ItemQuickLinkBinding;
import com.ducluanxutrieu.tikiapp.data.models.QuickLinkModel;
import com.ducluanxutrieu.tikiapp.utiu.ClickedListener;

import java.util.ArrayList;

public class QuickLinkAdapter extends RecyclerView.Adapter<QuickLinkAdapter.QuickLinkViewHolder> {
    private ArrayList<QuickLinkModel> listQuickLink;
    private final ClickedListener clickedListener;

    public QuickLinkAdapter(ClickedListener clickedListener) {
        this.listQuickLink  = new ArrayList<>();
        this.clickedListener = clickedListener;
    }

    void setData(ArrayList<QuickLinkModel> list){
        listQuickLink = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuickLinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQuickLinkBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_quick_link,
                parent,
                false
        );
        return new QuickLinkViewHolder(binding, clickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuickLinkViewHolder holder, int position) {
        holder.bindData(listQuickLink.get(position));
    }

    @Override
    public int getItemCount() {
        return listQuickLink.size();
    }

    public static class QuickLinkViewHolder extends RecyclerView.ViewHolder {
        private final ItemQuickLinkBinding mBinder;
        private final ClickedListener clickedListener;

        public QuickLinkViewHolder(@NonNull ItemQuickLinkBinding mBinder, ClickedListener clickedListener) {
            super(mBinder.getRoot());
            this.mBinder = mBinder;
            this.clickedListener = clickedListener;
        }

        void bindData(QuickLinkModel quickLinkModel) {
            Glide.with(mBinder.getRoot()).load(quickLinkModel.getImageUrl()).into(mBinder.ivQuickLink);
            mBinder.tvQuickLink.setText(quickLinkModel.getTitle());
            mBinder.getRoot().setOnClickListener(view -> {
                clickedListener.onClickedListener(quickLinkModel.getUrl());
            });
        }
    }
}
