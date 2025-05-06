package com.lacolinares.klima.di

import com.lacolinares.klima.data.repository.UserRepositoryImpl
import com.lacolinares.klima.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get()) }
}