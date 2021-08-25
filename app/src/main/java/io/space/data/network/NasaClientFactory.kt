package io.space.data.network

import io.space.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NasaClientFactory {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BuildConfig.NASA_API_BASE_URL)
        .client(getOkHttpClient())
        .build()

    fun <T> create(service: Class<T>): T = retrofit.create(service)

    private fun getOkHttpClient() = OkHttpClient.Builder().addInterceptor { chain ->
        chain.proceed(
            chain.request()
                .newBuilder()
                .url(getUrlWithKeyAsQueryParameter(chain))
                .build()
        )
    }.build()

    private fun getUrlWithKeyAsQueryParameter(chain: Interceptor.Chain) = chain.request().url
        .newBuilder()
        .addQueryParameter("api_key", BuildConfig.NASA_API_KEY)
        .build()
}
