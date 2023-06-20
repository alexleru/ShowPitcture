package com.alexleru.showpitcture.domain.usecases

import com.alexleru.showpitcture.domain.Repository
import com.alexleru.showpitcture.domain.entity.Picture

class SetFavoriteOfPictureUseCaseImpl(private val repository: Repository): SetFavoriteOfPictureUseCase {
    override fun invoke(picture: Picture) {
        return repository.setFavoriteOfPicture(picture)
    }
}