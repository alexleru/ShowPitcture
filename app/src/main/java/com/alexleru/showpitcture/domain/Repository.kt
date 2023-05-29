package com.alexleru.showpitcture.domain

import androidx.lifecycle.LiveData
import com.alexleru.showpitcture.domain.entity.Picture

interface Repository {

    fun getListOfPictures(): List<Picture>

}