package com.alexleru.showpitcture.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexleru.showpitcture.R
import com.alexleru.showpitcture.databinding.ItemPictureLayoutBinding
import com.alexleru.showpitcture.databinding.ItemTextLayoutBinding
import com.alexleru.showpitcture.presentation.view.entity.ItemDataViewModel
import com.alexleru.showpitcture.presentation.view.entity.ItemDataViewModel.PictureViewModel
import com.alexleru.showpitcture.presentation.view.entity.ItemDataViewModel.TextTitleViewModel


class PictureAdapter(
    private val clickOnItem: ((PictureViewModel) -> Unit),
    private val clickLongOnItem: ((PictureViewModel) -> Unit)
) :
    ListAdapter<ItemDataViewModel, RecyclerView.ViewHolder>(PictureDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PICTURE_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_picture_layout, parent, false)
                val bind = ItemPictureLayoutBinding.bind(view)
                ViewHolderPicture(bind, clickOnItem, clickLongOnItem)
            }

            TEXT_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_text_layout, parent, false)
                val bind = ItemTextLayoutBinding.bind(view)
                ViewHolderText(bind)
            }

            else -> throw RuntimeException("layout $viewType viewType no exists")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is ViewHolderPicture -> holder.bind(item as PictureViewModel)
            is ViewHolderText -> holder.bind(item as TextTitleViewModel)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val item = getItem(position)
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads[0] == true && holder is ViewHolderPicture) {
                holder.bindPayload((item as PictureViewModel))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is TextTitleViewModel -> TEXT_VIEW_TYPE
            is PictureViewModel -> PICTURE_VIEW_TYPE
        }
    }

    companion object {
        const val PICTURE_VIEW_TYPE = 100
        const val TEXT_VIEW_TYPE = 101
    }

}