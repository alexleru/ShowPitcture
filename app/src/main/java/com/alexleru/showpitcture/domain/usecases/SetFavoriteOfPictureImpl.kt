package com.alexleru.showpitcture.domain.usecases

import com.alexleru.showpitcture.domain.Repository
import com.alexleru.showpitcture.domain.entity.Picture

// TODO: UseCase?) Я бы придерживался одного нейминга)
class SetFavoriteOfPictureImpl(private val repository: Repository): SetFavoriteOfPicture {
    override fun invoke(picture: Picture) {
        return repository.setFavoriteOfPicture(picture)
    }
}