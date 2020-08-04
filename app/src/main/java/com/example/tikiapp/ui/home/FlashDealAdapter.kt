package com.example.tikiapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tikiapp.R
import com.example.tikiapp.databinding.ItemFlashDealBinding
import com.example.tikiapp.data.models.FlashDealModel
import com.uit.party.util.StringUtil
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class FlashDealAdapter : RecyclerView.Adapter<FlashDealAdapter.FlashDealViewHolder>() {
    private val listFlashDeal = ArrayList<FlashDealModel>()

    fun setData(list: ArrayList<FlashDealModel>) {
        listFlashDeal.clear()
        listFlashDeal.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashDealViewHolder {
        val binding = DataBindingUtil.inflate<ItemFlashDealBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_flash_deal,
            parent,
            false
        )
        return FlashDealViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listFlashDeal.size
    }

    override fun onBindViewHolder(holder: FlashDealViewHolder, position: Int) {
        holder.bindData(listFlashDeal[position])
    }

    class FlashDealViewHolder(private val item: ItemFlashDealBinding) :
        RecyclerView.ViewHolder(item.root) {
        private val formatter: NumberFormat = NumberFormat.getNumberInstance(Locale("vi"))

        fun bindData(flashDealModel: FlashDealModel) {
            Glide.with(item.root).load(flashDealModel.product.thumbnail_url).into(item.ivFlashDeal)
            val price = formatter.format(flashDealModel.product.price) + " ₫"
            val percent = 100 - flashDealModel.progress.percent
            item.tvPrice.text = price
            item.pbFlashDeal.progress = percent.toInt()

            val discountPercent = "-${flashDealModel.discount_percent}%"
            item.tvDiscountPercent.text = discountPercent

            var sellStatus =
                "${StringUtil.getString(R.string.sold)} ${flashDealModel.progress.qty_ordered}"

            if (percent > 50) {
                item.icHotDeal.visibility = View.VISIBLE
            } else item.icHotDeal.visibility = View.GONE

            if (percent < 5) {
                sellStatus = StringUtil.getString(R.string.just_opened_for_sale)
            }

            if (percent < 20) {
                item.pbFlashDeal.progress = 20
            }
            item.tvSellStatus.text = sellStatus
        }
    }
}
