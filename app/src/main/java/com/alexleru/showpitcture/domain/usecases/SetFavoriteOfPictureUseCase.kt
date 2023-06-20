package com.alexleru.showpitcture.domain.usecases

import com.alexleru.showpitcture.domain.entity.Picture

interface SetFavoriteOfPictureUseCase {
    operator fun invoke(picture: Picture)
}