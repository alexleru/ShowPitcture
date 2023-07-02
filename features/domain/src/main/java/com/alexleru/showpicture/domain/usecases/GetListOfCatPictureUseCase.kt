package com.alexleru.showpicture.domain.usecases

import com.alexleru.showpicture.domain.entity.CatPicture
import io.reactivex.rxjava3.core.Single

interface GetListOfCatPictureUseCase {
    operator fun invoke(): Single<List<CatPicture>>
}