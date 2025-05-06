package com.lacolinares.klima.di

import com.lacolinares.klima.domain.usecase.LoginUseCase
import com.lacolinares.klima.domain.usecase.SignUpUseCase
import com.lacolinares.klima.domain.usecase.session.ClearUserSessionUseCase
import com.lacolinares.klima.domain.usecase.session.GetUserSessionUseCase
import com.lacolinares.klima.domain.usecase.session.SaveUserSessionUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { SignUpUseCase(get()) }
    factory { LoginUseCase(get()) }

    factory { SaveUserSessionUseCase(get()) }
    factory { GetUserSessionUseCase(get()) }
    factory { ClearUserSessionUseCase(get()) }
}