package com.alexleru.showpictrure.presentation.view.modelView.arch

import com.alexleru.showpitcture.presentation.view.modelView.CatPictureView

data class PicturesViewState(
    val isRefreshing: Boolean = false,
    val catPictureView: List<CatPictureView>? = null,
    val globalError: Throwable? = null
    )