package com.alexleru.showpitcture.domain

import androidx.lifecycle.LiveData
import com.alexleru.showpitcture.domain.entity.ItemData
import com.alexleru.showpitcture.domain.entity.Picture

interface Repository {
    fun getListOfItem(): LiveData<List<ItemData>>

    fun setFavoriteOfPicture(picture: Picture)

    fun updateList()


}