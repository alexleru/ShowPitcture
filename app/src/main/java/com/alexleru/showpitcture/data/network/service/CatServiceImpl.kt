package com.alexleru.showpitcture.data.network.service

import com.alexleru.showpitcture.data.network.CatRetrofitBuilder
import com.alexleru.showpitcture.data.network.api.CatApi

class CatServiceImpl : CatService {

    private val catApi = CatRetrofitBuilder.buildRetrofit().create(CatApi::class.java)

    override fun getListOfCatPictures() = catApi.getListOfCatPictures()
}