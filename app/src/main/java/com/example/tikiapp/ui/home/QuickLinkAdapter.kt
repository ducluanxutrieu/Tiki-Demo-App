package com.example.tikiapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tikiapp.R
import com.example.tikiapp.databinding.ItemQuickLinkBinding
import com.example.tikiapp.models.QuickLinkModel

class QuickLinkAdapter : RecyclerView.Adapter<QuickLinkAdapter.QuickLinkViewHolder>() {
    private val listQuickLink = ArrayList<QuickLinkModel>()

    fun setData(list: ArrayList<QuickLinkModel>){
        listQuickLink.clear()
        listQuickLink.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuickLinkViewHolder {
        val binding = DataBindingUtil.inflate<ItemQuickLinkBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_quick_link,
            parent,
            false
        )
        return QuickLinkViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listQuickLink.size
    }

    override fun onBindViewHolder(holder: QuickLinkViewHolder, position: Int) {
        holder.bindData(listQuickLink[position])
    }

    class QuickLinkViewHolder(private val item: ItemQuickLinkBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bindData(quickLinkModel: QuickLinkModel) {
            Glide.with(item.root).load(quickLinkModel.image_url).into(item.ivQuickLink)
            item.tvQuickLink.text = quickLinkModel.title
        }
    }
}
