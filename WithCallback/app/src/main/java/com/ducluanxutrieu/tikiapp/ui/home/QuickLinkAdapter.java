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

import java.util.ArrayList;

public class QuickLinkAdapter extends RecyclerView.Adapter<QuickLinkAdapter.QuickLinkViewHolder> {
    private ArrayList<QuickLinkModel> listQuickLink = new ArrayList<>();

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
        return new QuickLinkViewHolder(binding);
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

        public QuickLinkViewHolder(@NonNull ItemQuickLinkBinding mBinder) {
            super(mBinder.getRoot());
            this.mBinder = mBinder;
        }

        void bindData(QuickLinkModel quickLinkModel) {
            Glide.with(mBinder.getRoot()).load(quickLinkModel.getImageUrl()).into(mBinder.ivQuickLink);
            mBinder.tvQuickLink.setText(quickLinkModel.getTitle());
        }
    }
}
