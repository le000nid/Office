package com.example.officemanagerapp.ui.services.market.addmarketorder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.ItemCategorySectionBinding
import com.example.officemanagerapp.models.Category
import com.example.officemanagerapp.responses.SectionCategories

class MarketCategoriesAdapter(val parentFragment: Fragment) : RecyclerView.Adapter<MarketCategoriesAdapter.ViewHolder>() {

    var sectionedCategories: List<SectionCategories> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val withDataBinding: ItemCategorySectionBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ViewHolder.LAYOUT,
            parent,
            false)
        return ViewHolder(withDataBinding)
    }

    override fun getItemCount() = sectionedCategories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.sectionTitle.text = sectionedCategories[position].title

            val sectionAdapter = MarketCategoriesSectionedAdapter(MarketCategoryItemClick { item ->

            })

            it.root.findViewById<RecyclerView>(R.id.sectionRV).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = sectionAdapter
            }

            sectionAdapter.categories = sectionedCategories[position].categories
        }
    }

    class ViewHolder(val viewDataBinding: ItemCategorySectionBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_category_section
        }
    }
}

class MarketCategoryItemClick(val block: (Category) -> Unit) {
    fun onClick(Category: Category) = block(Category)
}
