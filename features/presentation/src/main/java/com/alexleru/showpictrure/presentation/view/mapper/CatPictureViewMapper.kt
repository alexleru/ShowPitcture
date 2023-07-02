package com.alexleru.showpictrure.presentation.view.mapper

import com.alexleru.showpicture.domain.entity.CatPicture
import com.alexleru.showpitcture.presentation.view.modelView.CatPictureView

class CatPictureViewMapper {
    fun mapperToView(catPicture: CatPicture) =
        CatPictureView(
            id = catPicture.id,
            url = catPicture.url,
            width = catPicture.width,
            height = catPicture.height,
        )

    fun mapListCatPictureFromApiResponseToView(listCatPicture: List<CatPicture>) =
        listCatPicture.map(::mapperToView)
}