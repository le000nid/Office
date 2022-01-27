package com.example.officemanagerapp.util

import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.officemanagerapp.R
import com.example.officemanagerapp.models.OrderType
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("setRating")
fun setRating(view: ImageView, rate: Double) {
    when(rate.toInt()) {
        0 -> view.setBackgroundResource(R.drawable.ic_rate_0)
        1 -> view.setBackgroundResource(R.drawable.ic_rate_1)
        2 -> view.setBackgroundResource(R.drawable.ic_rate_2)
        3 -> view.setBackgroundResource(R.drawable.ic_rate_3)
        4 -> view.setBackgroundResource(R.drawable.ic_rate_4)
        5 -> view.setBackgroundResource(R.drawable.ic_rate_5)
    }
}

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("imageUri")
fun setImageUri(imageView: ImageView, uri: Uri?) {
    if (uri != null) {
        imageView.setImageURI(null)
        imageView.setImageURI(uri)
    }
}

@BindingAdapter("goneIfUriNull")
fun goneIfUriNull(imageView: ImageView, uri: Uri?) {
    imageView.visibility = if (uri == null) View.GONE else View.VISIBLE
}

@BindingAdapter("dateType")
fun dateType(cardView: CardView, type: Int) {
    when(type) {
        1 -> cardView.setCardBackgroundColor(Color.parseColor("#A5D6A7"))
        2 -> cardView.setCardBackgroundColor(Color.parseColor("#EF9A9A"))
    }
}

@BindingAdapter("categoryImage")
fun ImageView.setCategoryImage(item: Int) {
    setImageResource(when (item) {
        1 -> R.drawable.ic_water_drop
        2 -> R.drawable.ic_home
        else -> R.drawable.ic_broken_image
    })
}

@BindingAdapter("imageFromURL")
fun uploadImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("orderTypeIcon")
fun ImageView.setOrderTypeIcon(type: OrderType) {
    setImageResource(when(type) {
        OrderType.PLANNED -> R.drawable.ic_worker
        OrderType.MARKET -> R.drawable.ic_market
    })
}