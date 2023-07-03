package com.alexleru.showpicture.data.di

import com.alexleru.showpicture.data.CatRepositoryImpl
import com.alexleru.showpicture.data.CatRetrofitBuilder
import com.alexleru.showpicture.data.api.CatApi
import com.alexleru.showpicture.domain.CatRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val catsDataModule = module {
    single<CatRepository> {
        CatRepositoryImpl(
            catApi = get()
        )
    }

    single { CatRetrofitBuilder.buildRetrofit() }

    single<CatApi> {
        get<Retrofit>().create(CatApi::class.java)
    }
}