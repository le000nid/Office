package com.example.officemanagerapp.ui.tenants

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.ItemTenantBinding
import com.example.officemanagerapp.models.Tenant

class TenantsAdapter(
    val onClickListener: OnClickListener?
): RecyclerView.Adapter<TenantsAdapter.TenantViewHolder>() {

    var items = listOf<Tenant>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenantViewHolder {
        val binding: ItemTenantBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_tenant, parent, false)
        return TenantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TenantViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class TenantViewHolder(val binding: ItemTenantBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tenant: Tenant) {
            binding.tenant = tenant
            binding.tenantClick = onClickListener
        }
    }

    class OnClickListener(val clickListener: (item: Tenant) -> Unit) {
        fun onClick(item: Tenant) = clickListener(item)
    }
}