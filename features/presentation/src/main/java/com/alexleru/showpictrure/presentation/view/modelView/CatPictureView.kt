package com.alexleru.showpitcture.presentation.view.modelView
// TODO: Поехали пакеты после перемещения

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatPictureView(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
) : Parcelable