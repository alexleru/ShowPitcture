package com.alexleru.showpitcture.domain.usecases

import com.alexleru.showpitcture.domain.Repository
import com.alexleru.showpitcture.domain.entity.Picture

class SetFavoriteOfPictureImpl(private val repository: Repository): SetFavoriteOfPicture {
    override fun invoke(oldValue: Picture) {
        return repository.setFavoriteOfPicture(oldValue)
    }
}