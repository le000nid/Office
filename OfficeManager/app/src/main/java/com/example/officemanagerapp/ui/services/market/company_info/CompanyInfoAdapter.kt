package com.example.officemanagerapp.ui.services.market.company_info

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.ItemReviewBinding
import com.example.officemanagerapp.models.Review

class CompanyInfoAdapter(

): RecyclerView.Adapter<CompanyInfoAdapter.CompanyViewHolder>() {

    var items = listOf<Review>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding: ItemReviewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_review, parent, false)
        return CompanyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class CompanyViewHolder(val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) {
            binding.review = review
        }
    }
}