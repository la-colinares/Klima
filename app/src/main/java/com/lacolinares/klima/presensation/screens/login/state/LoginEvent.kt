package com.lacolinares.klima.presensation.screens.login.state

sealed class LoginEvent {
    data object OnValidateUserAuth: LoginEvent()
    data class OnEmailChanged(val email: String): LoginEvent()
    data class OnPasswordChanged(val password: String): LoginEvent()
    data object OnClearErrorMessage: LoginEvent()
    data object OnClearState: LoginEvent()
    data object OnLogin: LoginEvent()
}