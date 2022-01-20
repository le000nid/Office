package com.example.officemanagerapp.ui.services.market.companies_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.ItemCompanyBinding
import com.example.officemanagerapp.models.Company

class CompaniesListAdapter(
    val onClickListener: OnClickListener?
): RecyclerView.Adapter<CompaniesListAdapter.CompanyViewHolder>() {

    var items = listOf<Company>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding: ItemCompanyBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_company, parent, false)
        return CompanyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class CompanyViewHolder(val binding: ItemCompanyBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(company: Company) {
            binding.company = company
            binding.companyClick = onClickListener
        }
    }

    class OnClickListener(val clickListener: (item: Company) -> Unit) {
        fun onClick(item: Company) = clickListener(item)
    }
}