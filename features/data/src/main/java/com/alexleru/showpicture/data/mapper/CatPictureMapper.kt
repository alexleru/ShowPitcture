package com.alexleru.showpicture.data.mapper

import com.alexleru.showpicture.data.model.CatPictureResponse
import com.alexleru.showpicture.domain.entity.CatPicture

class CatPictureMapper {
    fun mapperToData(catPictureResponse: CatPictureResponse) =
        CatPicture(
            id = catPictureResponse.id,
            url = catPictureResponse.url,
            width = catPictureResponse.width,
            height = catPictureResponse.height
        )

    fun mapperToListData(listCatPictureResponse: List<CatPictureResponse>) =
        listCatPictureResponse.map(::mapperToData)
}