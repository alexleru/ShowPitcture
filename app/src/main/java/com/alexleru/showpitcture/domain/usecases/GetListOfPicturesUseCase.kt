package com.alexleru.showpitcture.domain.usecases

import com.alexleru.showpitcture.domain.Repository
import com.alexleru.showpitcture.domain.entity.Picture

// TODO: Покрыть интерфейсом
class GetListOfPicturesUseCase(private val repository: Repository) {
    operator fun invoke(): List<Picture> {
        return repository.getListOfPictures()
    }
}