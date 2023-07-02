package com.alexleru.showpicture.domain

import com.alexleru.showpicture.domain.entity.CatPicture
import io.reactivex.rxjava3.core.Single

interface CatRepository {
    fun getListOfCatPictures(): Single<List<CatPicture>>
}
