package com.go.it.planetapp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    var retrofit: Retrofit? = null
    private var client: OkHttpClient? = null
    fun build(): PlanetApi {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            if (client == null)
                client = OkHttpClient.Builder()

                    .addInterceptor(interceptor)

                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(100, TimeUnit.SECONDS)

                    .build()

            retrofit = Retrofit.Builder()
                .client(client)

                .baseUrl(ApiEndPoints.PLANET_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())

                .build()
        }
        return retrofit!!.create(PlanetApi::class.java)
    }
}