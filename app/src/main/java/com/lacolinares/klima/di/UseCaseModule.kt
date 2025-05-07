package com.lacolinares.klima.di

import com.lacolinares.klima.domain.usecase.LoginUseCase
import com.lacolinares.klima.domain.usecase.SignUpUseCase
import com.lacolinares.klima.domain.usecase.session.ClearUserSessionUseCase
import com.lacolinares.klima.domain.usecase.session.GetUserSessionUseCase
import com.lacolinares.klima.domain.usecase.session.SaveUserSessionUseCase
import com.lacolinares.klima.domain.usecase.weather.GetWeatherUseCase
import com.lacolinares.klima.domain.usecase.weather.GetWeathersUseCase
import com.lacolinares.klima.domain.usecase.weather.SaveWeatherUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { SignUpUseCase(get()) }
    factory { LoginUseCase(get()) }

    factory { SaveUserSessionUseCase(get()) }
    factory { GetUserSessionUseCase(get()) }
    factory { ClearUserSessionUseCase(get()) }

    factory { SaveWeatherUseCase(get()) }
    factory { GetWeatherUseCase(get()) }
    factory { GetWeathersUseCase(get()) }
}