package com.alexleru.showpictrure.presentation.adapters

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alexleru.showpictrure.presentation.databinding.ItemPictureLayoutBinding
import com.alexleru.showpitcture.presentation.view.modelView.CatPictureView
import com.bumptech.glide.Glide

class ViewHolderPicture(
    private val binding: ItemPictureLayoutBinding,
    private val clickOnItem: (CatPictureView) -> Unit,
    private val clickLongOnItem: (CatPictureView) -> Unit
) : ViewHolder(binding.root) {

    fun bind(
        picture: CatPictureView
    ) {
        setupMainImage(picture)
        //setupFavoriteImage(picture.favorite)
        setupListener(picture)
    }

    fun bindPayload(picture: CatPictureView) {
        //setupFavoriteImage(picture.favorite)
        setupListener(picture)
    }

    private fun setupMainImage(
        picture: CatPictureView
    ) {
        binding.imageViewMain.textDate = picture.url
        loadImage(picture)
    }

    private fun loadImage(picture: CatPictureView) {
        Glide.with(binding.root.context)
            .load(picture.url)
            .placeholder(common.uikit.R.drawable.image_loading_placeholder)
            .error(common.uikit.R.drawable.image_error_placeholder)
            .into(binding.imageViewMain.getImageCompound())
    }

    private fun setupListener(picture: CatPictureView) {
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