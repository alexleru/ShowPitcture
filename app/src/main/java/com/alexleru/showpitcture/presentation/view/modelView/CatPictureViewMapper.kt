package com.alexleru.showpitcture.presentation.view.modelView

import com.alexleru.showpitcture.data.network.model.CatPictureResponse

// TODO: Здесь нужно мапить из domain во view и наоборот
// так же можно сделать вот так
// fun CatPictureResponse.toUiModel(): CatPictureView = ...

class CatPictureViewMapper {
    fun mapCatPictureFromApiResponseToView(catPictureResponse: CatPictureResponse) =
        CatPictureView(
            id = catPictureResponse.id,
            url = catPictureResponse.url,
            width = catPictureResponse.width,
            height = catPictureResponse.height,
        )

    // TODO: Можно вот так listCatPictureResponse.map(::mapCatPictureFromApiResponseToView)
    fun mapListCatPictureFromApiResponseToView(listCatPictureResponse: List<CatPictureResponse>) =
        listCatPictureResponse.map { mapCatPictureFromApiResponseToView(it) }
}