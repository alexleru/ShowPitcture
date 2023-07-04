package com.alexleru.showpicture.domain.usecases

import com.alexleru.showpicture.domain.CatRepository
import com.alexleru.showpicture.domain.entity.CatPicture
import io.reactivex.rxjava3.core.Single

// TODO: internal
class GetListOfCatPictureUseCaseImpl(private val repository: CatRepository) :
    GetListOfCatPictureUseCase {
    override fun invoke(): Single<List<CatPicture>> {
        return repository.getListOfCatPictures()
    }
}