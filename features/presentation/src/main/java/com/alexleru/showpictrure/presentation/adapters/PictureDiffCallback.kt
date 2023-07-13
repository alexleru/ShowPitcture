package com.alexleru.showpictrure.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.alexleru.showpitcture.presentation.view.modelView.CatPictureView

class PictureDiffCallback : DiffUtil.ItemCallback<CatPictureView>() {
    override fun areItemsTheSame(oldItem: CatPictureView, newItem: CatPictureView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CatPictureView,
        newItem: CatPictureView
    ): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: CatPictureView, newItem: CatPictureView): Any? {
        return if (oldItem.url != newItem.url
        ) {
            true
        } else {
            null
        }
    }
}