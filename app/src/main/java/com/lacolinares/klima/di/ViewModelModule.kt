package com.lacolinares.klima.di

import com.lacolinares.klima.presensation.screens.login.LoginViewModel
import com.lacolinares.klima.presensation.screens.main.home.HomeViewModel
import com.lacolinares.klima.presensation.screens.signup.SignUpViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { LoginViewModel(get(), get(), get()) }

    viewModel { HomeViewModel(get()) }
}