package com.alexleru.showpitcture.data.network.service

import com.alexleru.showpitcture.data.network.CatRetrofitBuilder
import com.alexleru.showpitcture.data.network.api.CatApi

class CatServiceImpl(
    private val catApi: CatApi = CatRetrofitBuilder.buildRetrofit().create(CatApi::class.java)
) : CatService {

    override fun getListOfCatPictures() = catApi.getListOfCatPictures()
}