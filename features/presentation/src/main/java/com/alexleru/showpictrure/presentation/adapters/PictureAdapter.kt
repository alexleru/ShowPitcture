package com.alexleru.showpictrure.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexleru.showpictrure.presentation.R
import com.alexleru.showpictrure.presentation.databinding.ItemPictureLayoutBinding
import com.alexleru.showpitcture.presentation.view.modelView.CatPictureView


class PictureAdapter(
    private val clickOnItem: ((CatPictureView) -> Unit),
    private val clickLongOnItem: ((CatPictureView) -> Unit)
) :
    ListAdapter<CatPictureView, RecyclerView.ViewHolder>(PictureDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PICTURE_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_picture_layout, parent, false)
                val bind = ItemPictureLayoutBinding.bind(view)
                ViewHolderPicture(bind, clickOnItem, clickLongOnItem)
            }

            else -> throw RuntimeException("layout $viewType viewType no exists")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is ViewHolderPicture -> holder.bind(item)
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
                holder.bindPayload(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CatPictureView -> PICTURE_VIEW_TYPE
            else -> throw RuntimeException("Not Found ViewType")
        }
    }

    companion object {
        const val PICTURE_VIEW_TYPE = 100
    }

}