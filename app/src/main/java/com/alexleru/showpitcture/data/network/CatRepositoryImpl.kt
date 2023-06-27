package com.alexleru.showpitcture.data.network

import com.alexleru.showpitcture.domain.CatRepository
import com.alexleru.showpitcture.domain.entity.CatPicture
import io.reactivex.rxjava3.core.Single

class CatRepositoryImpl : CatRepository {
    override fun getListOfCatPictures(): Single<List<CatPicture>> {
        TODO("Not yet implemented")
    }


}