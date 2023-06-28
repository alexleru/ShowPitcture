package com.alexleru.showpitcture.data.network.service

import com.alexleru.showpitcture.data.network.CatRetrofitBuilder
import com.alexleru.showpitcture.data.network.api.CatApi

class CatServiceImpl : CatService {

    // TODO: Давай запихивать в конструкторы
    private val catApi = CatRetrofitBuilder.buildRetrofit().create(CatApi::class.java)

    override fun getListOfCatPictures() = catApi.getListOfCatPictures()
}