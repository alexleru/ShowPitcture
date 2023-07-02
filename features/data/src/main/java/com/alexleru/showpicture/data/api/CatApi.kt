package com.alexleru.showpicture.data.api

import com.alexleru.showpicture.data.model.CatPictureResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("search")
    fun getListOfCatPictures(
        @Query(LIMIT) limit: Int = LIMIT_VALUE
    ): Single<List<CatPictureResponse>>

    companion object {
        private const val LIMIT = "limit"
        private const val LIMIT_VALUE = 20
    }
}