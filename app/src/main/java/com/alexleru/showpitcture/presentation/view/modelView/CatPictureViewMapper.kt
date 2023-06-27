package com.alexleru.showpitcture.presentation.view.modelView

import com.alexleru.showpitcture.data.network.model.CatPictureResponse

class CatPictureViewMapper {
    fun mapCatPictureFromApiResponseToView(catPictureResponse: CatPictureResponse) =
        CatPictureView(
            id = catPictureResponse.id,
            url = catPictureResponse.url,
            width = catPictureResponse.width,
            height = catPictureResponse.height,
        )

    fun mapListCatPictureFromApiResponseToView(listCatPictureResponse: List<CatPictureResponse>) =
        listCatPictureResponse.map { mapCatPictureFromApiResponseToView(it) }
}