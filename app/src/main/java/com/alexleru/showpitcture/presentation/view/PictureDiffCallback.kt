package com.alexleru.showpitcture.presentation.view

import androidx.recyclerview.widget.DiffUtil
import com.alexleru.showpitcture.presentation.view.entity.ItemDataViewModel
import com.alexleru.showpitcture.presentation.view.entity.ItemDataViewModel.PictureViewModel

class PictureDiffCallback : DiffUtil.ItemCallback<ItemDataViewModel>() {
    override fun areItemsTheSame(oldItem: ItemDataViewModel, newItem: ItemDataViewModel): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(
        oldItem: ItemDataViewModel,
        newItem: ItemDataViewModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: ItemDataViewModel, newItem: ItemDataViewModel): Any? {
        return if (oldItem is PictureViewModel
            && newItem is PictureViewModel
            && oldItem.favorite != newItem.favorite
        ) {
            true
        } else {
            null
        }
    }
}