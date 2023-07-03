package com.alexleru.showpicture.data

import com.alexleru.showpicture.data.api.CatApi
import com.alexleru.showpicture.data.mapper.CatPictureMapper
import com.alexleru.showpicture.domain.CatRepository

class CatRepositoryImpl(
    private val catApi: CatApi
) : CatRepository {

    private val catPictureMapper = CatPictureMapper()
    override fun getListOfCatPictures() =
        catApi.getListOfCatPictures().map { catPictureMapper.mapperToListData(it) }


}