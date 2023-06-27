package com.alexleru.showpitcture.domain.usecases

import com.alexleru.showpitcture.domain.CatRepository
import com.alexleru.showpitcture.domain.entity.CatPicture
import io.reactivex.rxjava3.core.Single

class GetListOfCatPictureUseCaseImpl(private val repository: CatRepository) :
    GetListOfCatPictureUseCase {
    override fun invoke(): Single<List<CatPicture>> {
        return repository.getListOfCatPictures()
    }
}