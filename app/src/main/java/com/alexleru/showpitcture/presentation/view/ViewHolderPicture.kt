package com.alexleru.showpitcture.presentation.view

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alexleru.showpitcture.databinding.ItemPictureLayoutBinding
import com.alexleru.showpitcture.formatDate
import com.alexleru.showpitcture.fromAssertToDrawable
import com.alexleru.showpitcture.presentation.view.entity.ItemDataViewModel.*

class ViewHolderPicture(
    private val binding: ItemPictureLayoutBinding,
    private val clickOnItem: (PictureViewModel) -> Unit,
    private val clickLongOnItem: (PictureViewModel) -> Unit
) : ViewHolder(binding.root) {

    fun bind(
        picture: PictureViewModel
    ) {
        setupMainImage(picture)
        setupFavoriteImage(picture.favorite)
        setupListener(picture)
    }

    fun bindPayload(picture: PictureViewModel) {
        setupFavoriteImage(picture.favorite)
        setupListener(picture)
    }

    private fun setupMainImage(
        picture: PictureViewModel
    ) {
        val drawable = binding.root.context.fromAssertToDrawable(picture.url)
        with(binding.imageViewMain) {
            setImageCompound(drawable)
            textDate = picture.date.formatDate()
        }
    }


    private fun setupListener(picture: PictureViewModel) {
        with(binding.imageViewMain) {
            setOnClickListener { clickOnItem.invoke(picture) }
            setOnLongClickListener {
                clickLongOnItem.invoke(picture)
                true
            }
        }
    }

    private fun setupFavoriteImage(isFavorite: Boolean) {
        if (isFavorite) {
            binding.imageViewFavorite.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            binding.imageViewFavorite.setImageResource(android.R.drawable.btn_star_big_off)
        }
    }


}