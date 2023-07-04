package com.alexleru.showpicture.data

import com.alexleru.showpicture.data.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// TODO: internal
object CatRetrofitBuilder {
    private const val BASE_URL = "https://api.thecatapi.com/v1/images/"

    // TODO: Закинуть интерцепторы под DI
    private fun builderOkhttp(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    fun buildRetrofit(): Retrofit = Retrofit.Builder()
        .client(builderOkhttp())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}