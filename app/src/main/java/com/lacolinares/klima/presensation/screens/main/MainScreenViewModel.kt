package com.lacolinares.klima.presensation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacolinares.klima.domain.usecase.GetUserFullNameUseCase
import com.lacolinares.klima.domain.usecase.session.ClearUserSessionUseCase
import com.lacolinares.klima.domain.usecase.session.GetUserSessionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val getUserSessionUseCase: GetUserSessionUseCase,
    private val getUserFullNameUseCase: GetUserFullNameUseCase,
    private val clearUserSessionUseCase: ClearUserSessionUseCase,
): ViewModel() {

    private val _userFullName = MutableStateFlow("")
    val userFullName = _userFullName.asStateFlow()

    fun loadUserFullName() {
        viewModelScope.launch {
            getUserSessionUseCase.execute().collectLatest { session ->
                session?.let {
                    val fullName = getUserFullNameUseCase.execute(session.email)
                    _userFullName.update { fullName }
                }
            }
        }
    }

    fun onLogout() {
        viewModelScope.launch {
            clearUserSessionUseCase.execute()
        }
    }
}