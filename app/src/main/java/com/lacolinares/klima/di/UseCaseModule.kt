package com.lacolinares.klima.di

import com.lacolinares.klima.domain.usecase.SignUpUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { SignUpUseCase(get()) }
}