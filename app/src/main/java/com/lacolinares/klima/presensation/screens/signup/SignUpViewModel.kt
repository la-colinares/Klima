package com.lacolinares.klima.presensation.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacolinares.klima.domain.usecase.SignUpUseCase
import com.lacolinares.klima.presensation.screens.signup.state.SignUpEvent
import com.lacolinares.klima.presensation.screens.signup.state.SignUpFormState
import com.lacolinares.klima.presensation.screens.signup.state.SignUpUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(private val signUpUseCase: SignUpUseCase): ViewModel() {

    private val _state = MutableStateFlow(SignUpUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: SignUpEvent){
        when (event) {
            is SignUpEvent.OnFullNameChanged -> updateForm { it.copy(fullName = event.fullName) }
            is SignUpEvent.OnEmailChanged -> updateForm { it.copy(email = event.email) }
            is SignUpEvent.OnPasswordChanged -> updateForm { it.copy(password = event.password) }
            is SignUpEvent.OnConfirmPasswordChanged -> updateForm { it.copy(confirmPassword = event.confirmPassword) }
            is SignUpEvent.OnSubmit -> submitForm()
        }
    }

    private fun updateForm(update: (SignUpFormState) -> SignUpFormState) {
        _state.update { current ->
            current.copy(form = update(current.form), fieldErrors = emptyMap(), errorMessage = null)
        }
    }

    private fun submitForm() {
        viewModelScope.launch {
            val form = _state.value.form

            val result = signUpUseCase.execute(
                fullName = form.fullName,
                email = form.email,
                password = form.password,
                confirmPassword = form.confirmPassword
            )

            if (result.success) {
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