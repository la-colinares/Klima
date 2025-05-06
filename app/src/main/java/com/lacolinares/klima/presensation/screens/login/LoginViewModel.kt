package com.lacolinares.klima.presensation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacolinares.klima.domain.usecase.LoginUseCase
import com.lacolinares.klima.domain.usecase.session.SaveUserSessionUseCase
import com.lacolinares.klima.presensation.screens.login.state.LoginEvent
import com.lacolinares.klima.presensation.screens.login.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val saveUserSessionUseCase: SaveUserSessionUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> _state.update { it.copy(email = event.email) }
            is LoginEvent.OnPasswordChanged -> _state.update { it.copy(password = event.password) }
            is LoginEvent.OnLogin -> login()
        }
    }

    private fun login() {
        viewModelScope.launch {
            val result = loginUseCase.execute(email = state.value.email, password = state.value.password)

            if (result.success) {
                saveUserSessionUseCase.execute(email = state.value.email)

                _state.update {
                    it.copy(isSuccess = true, fieldErrors = emptyMap(), errorMessage = null)
                }
            } else {
                _state.update {
                    it.copy(isSuccess = false, fieldErrors = result.fieldErrors, errorMessage = result.errorMessage)
                }
            }
        }
    }
}