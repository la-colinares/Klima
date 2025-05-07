package com.lacolinares.klima.di

import com.lacolinares.klima.data.repository.UserRepositoryImpl
import com.lacolinares.klima.data.repository.WeatherRepositoryImpl
import com.lacolinares.klima.domain.repository.UserRepository
import com.lacolinares.klima.domain.repository.WeatherRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get(), get(named("openWeatherApiKey"))) }
}