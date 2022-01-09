package com.example.officemanagerapp.ui.services.order_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.ItemPhotoBinding
import com.example.officemanagerapp.models.Photo

class PhotoDiffCallback(
    private val oldList: List<Photo>,
    private val newList: List<Photo>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPhoto = oldList[oldItemPosition]
        val newPhoto = newList[newItemPosition]
        return oldPhoto.uri == newPhoto.uri
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPhoto = oldList[oldItemPosition]
        val newPhoto = newList[newItemPosition]
        return oldPhoto == newPhoto
    }

}

class PhotoAdapter(
    val onClickListener: PhotoRemoveClick
) : RecyclerView.Adapter<PhotoAdapter.PhotosViewHolder>() {

    var photos: List<Photo> = emptyList()
        set(newValue) {
            val diffCallback = PhotoDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val binding: ItemPhotoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_photo, parent, false)
        return PhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    inner class PhotosViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            binding.photo = photo
            binding.photoClick = onClickListener
        }
    }

    override fun getItemCount(): Int = photos.size

    class PhotoRemoveClick(val block: (Photo) -> Unit) {
        fun onClick(photo: Photo) = block(photo)
    }
}
