package com.lacolinares.klima.presensation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacolinares.klima.domain.usecase.session.ClearUserSessionUseCase
import kotlinx.coroutines.launch

class MainScreenViewModel(private val clearUserSessionUseCase: ClearUserSessionUseCase): ViewModel() {

    fun onLogout() {
        viewModelScope.launch {
            clearUserSessionUseCase.execute()
        }
    }
}