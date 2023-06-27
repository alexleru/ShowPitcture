package com.alexleru.showpitcture.data.network.service

import com.alexleru.showpitcture.data.network.model.CatPictureResponse
import io.reactivex.rxjava3.core.Single

interface CatService {
    fun getListOfCatPictures(): Single<List<CatPictureResponse>>
}