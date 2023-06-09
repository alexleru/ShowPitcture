package com.alexleru.showpitcture.domain

import androidx.lifecycle.LiveData
import com.alexleru.showpitcture.domain.entity.ItemData
import com.alexleru.showpitcture.domain.entity.Picture
import com.alexleru.showpitcture.domain.entity.TextTitle

interface Repository {
    fun getListOfItem(): LiveData<List<ItemData>>

    fun setFavoriteOfPicture(oldValue: Picture)

    fun updateList()


}