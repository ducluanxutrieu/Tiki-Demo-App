package com.ducluanxutrieu.tikiapp.ui.home;

import android.icu.text.NumberFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ducluanxutrieu.tikiapp.R;
import com.ducluanxutrieu.tikiapp.data.models.FlashDealModel;
import com.ducluanxutrieu.tikiapp.databinding.ItemFlashDealBinding;
import com.ducluanxutrieu.tikiapp.utiu.Util;

import java.util.ArrayList;
import java.util.Locale;

public class FlashDealAdapter extends RecyclerView.Adapter<FlashDealAdapter.FlashDealViewHolder> {
    private final ArrayList<FlashDealModel> listFlashDeal = new ArrayList<>();

    void setData(ArrayList<FlashDealModel> list) {
        listFlashDeal.clear();
        listFlashDeal.addAll(list);
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public FlashDealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFlashDealBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
        R.layout.item_flash_deal,
                parent,
                false
        );
        return new FlashDealViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull FlashDealViewHolder holder, int position) {
        holder.bindData(listFlashDeal.get(position));
    }

    @Override
    public int getItemCount() {
        return listFlashDeal.size();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static class FlashDealViewHolder extends RecyclerView.ViewHolder {
        private final NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("vi"));
        final ItemFlashDealBinding item;

        public FlashDealViewHolder(@NonNull ItemFlashDealBinding item) {
            super(item.getRoot());
            this.item = item;
        }

        void bindData(FlashDealModel flashDealModel) {
            Glide.with(item.getRoot()).load(flashDealModel.getProduct().getThumbnailUrl()).into(item.ivFlashDeal);
            String price = formatter.format(flashDealModel.getProduct().getPrice()) + " ₫";
            int percent = (int) (100 - flashDealModel.getProductProgress().getPercent());
            item.tvPrice.setText(price);
            item.pbFlashDeal.setProgress(percent);

            String discountPercent = "-" + flashDealModel.getDiscountPercent() + "%";
            item.tvDiscountPercent.setText(discountPercent);

            String sellStatus =
                Util.getString(R.string.sold) + " " + flashDealModel.getProductProgress().getQtyOrdered();

            if (percent > 50) {
                item.icHotDeal.setVisibility(View.VISIBLE);
            } else item.icHotDeal.setVisibility(View.GONE);

            if (percent < 5) {
                sellStatus = Util.getString(R.string.just_opened_for_sale);
            }

            if (percent < 20) {
                item.pbFlashDeal.setProgress(20);
            }
            item.tvSellStatus.setText(sellStatus);
        }
    }
}
