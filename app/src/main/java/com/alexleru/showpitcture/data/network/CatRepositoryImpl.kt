package com.alexleru.showpitcture.data.network

import com.alexleru.showpitcture.data.network.service.CatService
import com.alexleru.showpitcture.data.network.service.CatServiceImpl
import com.alexleru.showpitcture.domain.CatRepository
import com.alexleru.showpitcture.domain.entity.CatPicture
import io.reactivex.rxjava3.core.Single

class CatRepositoryImpl(
    private val service: CatService = CatServiceImpl()
) : CatRepository {


    // TODO: Сделаем здесь BehaviorSubject, getList будет обновлять его, а вьюмодельки подписываться на его изменения
    // также будем обновлять favorite у сабжекта
    // Response будет мапиться в CatPicture из domain

    // Completable
    override fun getListOfCatPictures(): Single<List<CatPicture>> {
        service.getListOfCatPictures()
            .doOnSuccess { cats ->

            }
            .ignoreElement()
        TODO("Not yet implemented")
    }
}