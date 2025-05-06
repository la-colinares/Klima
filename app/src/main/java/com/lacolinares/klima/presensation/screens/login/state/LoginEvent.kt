package com.lacolinares.klima.presensation.screens.login.state

sealed class LoginEvent {
    data class OnEmailChanged(val email: String): LoginEvent()
    data class OnPasswordChanged(val password: String): LoginEvent()
    data object OnLogin: LoginEvent()
}