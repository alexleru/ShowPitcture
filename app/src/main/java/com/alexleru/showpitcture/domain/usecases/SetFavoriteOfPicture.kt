package com.alexleru.showpitcture.domain.usecases

import com.alexleru.showpitcture.domain.entity.Picture

interface SetFavoriteOfPicture {
    operator fun invoke(picture: Picture)
}