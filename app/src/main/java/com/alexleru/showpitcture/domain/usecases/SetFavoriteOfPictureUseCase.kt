package com.alexleru.showpitcture.domain.usecases

import com.alexleru.showpitcture.domain.entity.ItemData.Picture

interface SetFavoriteOfPictureUseCase {
    operator fun invoke(picture: Picture)
}