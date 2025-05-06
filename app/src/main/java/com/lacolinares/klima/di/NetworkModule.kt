package com.lacolinares.klima.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lacolinares.klima.BuildConfig
import com.lacolinares.klima.data.remote.api.OpenWeatherApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {

    single(named("httpLogger")) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single(named("okHttpClient")) {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>(named("httpLogger")))
            .build()
    }

    single {
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(get<OkHttpClient>(named("okHttpClient")))
            .build()
            .create(OpenWeatherApi::class.java)
    }

    single(named("openWeatherApiKey")) { BuildConfig.API_KEY }
}