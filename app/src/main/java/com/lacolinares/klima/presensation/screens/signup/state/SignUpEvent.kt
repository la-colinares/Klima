package com.lacolinares.klima.presensation.screens.signup.state

sealed class SignUpEvent {
    data class OnFullNameChanged(val fullName: String) : SignUpEvent()
    data class OnEmailChanged(val email: String) : SignUpEvent()
    data class OnPasswordChanged(val password: String) : SignUpEvent()
    data class OnConfirmPasswordChanged(val confirmPassword: String) : SignUpEvent()
    data object OnSubmit : SignUpEvent()
}