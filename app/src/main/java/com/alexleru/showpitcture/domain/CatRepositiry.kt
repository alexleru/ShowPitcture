package com.alexleru.showpitcture.domain

import com.alexleru.showpitcture.domain.entity.CatPicture
import io.reactivex.rxjava3.core.Single

interface CatRepository {
    fun getListOfCatPictures(): Single<List<CatPicture>>
}
