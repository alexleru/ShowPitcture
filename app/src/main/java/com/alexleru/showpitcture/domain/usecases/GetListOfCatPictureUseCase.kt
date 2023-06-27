package com.alexleru.showpitcture.domain.usecases

import com.alexleru.showpitcture.domain.entity.CatPicture
import io.reactivex.rxjava3.core.Single

interface GetListOfCatPictureUseCase {
    operator fun invoke(): Single<List<CatPicture>>
}