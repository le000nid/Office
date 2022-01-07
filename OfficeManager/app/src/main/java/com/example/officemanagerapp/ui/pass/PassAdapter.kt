package com.example.officemanagerapp.ui.pass

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.ItemPassBinding
import com.example.officemanagerapp.models.Pass

class PassAdapter(
    val onClickListener: OnClickListener?
): RecyclerView.Adapter<PassAdapter.PassViewHolder>() {

    var items = listOf<Pass>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassViewHolder {
        val binding: ItemPassBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_pass, parent, false)
        return PassViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PassViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class PassViewHolder(val binding: ItemPassBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pass: Pass) {
            binding.pass = pass
            binding.passClick = onClickListener
        }
    }

    class OnClickListener(val clickListener: (item: Pass) -> Unit) {
        fun onClick(item: Pass) = clickListener(item)
    }
}