package com.example.officemanagerapp.ui.services.planned.category

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.ItemCategoryCardBinding
import com.example.officemanagerapp.models.Category


class PlannedCategoriesAdapter(
    val onClickListener: OnClickListener?
) : RecyclerView.Adapter<PlannedCategoriesAdapter.CategoriesViewHolder>() {

    var items: List<Category> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding: ItemCategoryCardBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_category_card, parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class CategoriesViewHolder(val binding: ItemCategoryCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.category = category
            binding.click = onClickListener
        }
    }

    class OnClickListener(val clickListener: (Category: Category) -> Unit) {
        fun onClick(category: Category) = clickListener(category)
    }
}