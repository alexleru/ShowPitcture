package com.alexleru.showpitcture.data

import com.alexleru.showpitcture.domain.Repository
import com.alexleru.showpitcture.domain.entity.Picture

class RepositoryImpl : Repository {

    override fun getListOfPictures(): List<Picture> {
        val pictures = mutableListOf<Picture>()
        pictures.add(Picture("img_0.png"))
        pictures.add(Picture("img_1.png"))
        pictures.add(Picture("img_2.png"))
        pictures.add(Picture("img_3.png"))
        pictures.add(Picture("img_4.png"))
        pictures.add(Picture("img_5.png"))
        pictures.add(Picture("img_0.png"))
        pictures.add(Picture("img_1.png"))
        pictures.add(Picture("img_2.png"))
        pictures.add(Picture("img_3.png"))
        pictures.add(Picture("img_4.png"))
        pictures.add(Picture("img_5.png"))
        pictures.add(Picture("img_0.png"))
        pictures.add(Picture("img_1.png"))
        pictures.add(Picture("img_2.png"))
        pictures.add(Picture("img_3.png"))
        pictures.add(Picture("img_4.png"))
        pictures.add(Picture("img_5.png"))
        return pictures.toList()

    }
}