package com.alexleru.showpicture.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

private const val HEADER_API_KEY = "x-api-key"
private const val KEY = "live_Gg6FZAtQralbxZKoKLONJNi7qYczQ2RshH1ZSiKoiO9VWz1qZkGqmSuK8Wz2u1En"

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(HEADER_API_KEY, KEY)
            .build()
        return chain.proceed(request)
    }
}