package com.alexleru.showpitcture.presentation.view

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alexleru.showpitcture.databinding.ItemPictureLayoutBinding
import com.alexleru.showpitcture.domain.entity.Picture
import com.alexleru.showpitcture.fromAssertToDrawable

class ViewHolderPicture(
    private val binding: ItemPictureLayoutBinding,
    private val clickOnItem: (Picture) -> Unit,
    private val clickLongOnItem: (Picture) -> Unit
) : ViewHolder(binding.root) {

    fun bind(
        picture: Picture
    ) {
        setupMainImage(picture)
        setupFavoriteImage(picture.favorite)
    }

    fun bindPayload(isFavorite: Boolean) {
        setupFavoriteImage(isFavorite)
    }

    private fun setupMainImage(
        picture: Picture
    ) {
        val drawable = binding.root.context.fromAssertToDrawable(picture.url)
        with(binding.imageViewMain) {
            textDate = picture.date.toString()
            setImageCompound(drawable)
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