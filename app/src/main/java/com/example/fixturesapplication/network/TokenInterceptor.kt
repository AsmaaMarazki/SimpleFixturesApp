package com.example.fixturesapplication.network

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Auth-Token", "81c746d6138a43b58b77572bd0c1b64d").build()
        return chain.proceed(request)
    }
}