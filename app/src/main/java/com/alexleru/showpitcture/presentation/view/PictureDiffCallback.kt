package com.alexleru.showpitcture.presentation.view

import androidx.recyclerview.widget.DiffUtil
import com.alexleru.showpitcture.domain.entity.ItemData
import com.alexleru.showpitcture.domain.entity.Picture

class PictureDiffCallback : DiffUtil.ItemCallback<ItemData>() {
    override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: ItemData, newItem: ItemData): Any? {
        return if (oldItem is Picture && newItem is Picture && oldItem.favorite != newItem.favorite) {
            true
        } else {
            null
        }
    }
}