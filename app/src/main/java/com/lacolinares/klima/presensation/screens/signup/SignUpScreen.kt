package com.lacolinares.klima.presensation.screens.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.klima.domain.validation.FieldType
import com.lacolinares.klima.presensation.composables.KlimaPasswordField
import com.lacolinares.klima.presensation.composables.KlimaTextField
import com.lacolinares.klima.presensation.screens.signup.state.SignUpEvent
import com.lacolinares.klima.presensation.screens.signup.state.SignUpUiState
import com.lacolinares.klima.presensation.theme.Mirage
import com.lacolinares.klima.presensation.theme.Neptune
import com.lacolinares.klima.presensation.theme.Silver
import com.lacolinares.klima.presensation.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    state: SignUpUiState,
    onEvent: (SignUpEvent) -> Unit,
    onBack: () -> Unit = {},
    onSignUpSuccess: () -> Unit = {}
) {
    val context = LocalContext.current

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess){
            onSignUpSuccess.invoke()
        }
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Mirage,
                    navigationIconContentColor = Neptune,
                    titleContentColor = Neptune
                ),
                title = {
                    Text(text = "Sign Up")
                },
                navigationIcon = {
                    IconButton(onClick = { onBack.invoke() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        containerColor = Mirage
    ) { innerPadding ->

        val padding = PaddingValues(
            start = 24.dp,
            top = innerPadding.calculateTopPadding(),
            end = 24.dp,
            bottom = innerPadding.calculateBottomPadding()
        )

        Column(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Start getting weather updates instantly.",
                modifier = Modifier.fillMaxWidth(),
                color = Silver,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            KlimaTextField(
                value = state.form.fullName,
                onValueChange = { onEvent(SignUpEvent.OnFullNameChanged(it)) },
                label = "Full Name",
                placeholder = "Enter your full name",
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                isError = !state.fieldErrors[FieldType.FULL_NAME].isNullOrEmpty(),
                errorMessage = state.fieldErrors[FieldType.FULL_NAME]
            )
            KlimaTextField(
                value = state.form.email,
                onValueChange = { onEvent(SignUpEvent.OnEmailChanged(it)) },
                label = "Email",
                placeholder = "Enter your email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next) ,
                isError = !state.fieldErrors[FieldType.EMAIL].isNullOrEmpty(),
                errorMessage = state.fieldErrors[FieldType.EMAIL]
            )
            KlimaPasswordField(
                value = state.form.password,
                onValueChange = { onEvent(SignUpEvent.OnPasswordChanged(it)) },
                label = "Password",
                placeholder = "Enter your password",
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                isError = !state.fieldErrors[FieldType.PASSWORD].isNullOrEmpty(),
                errorMessage = state.fieldErrors[FieldType.PASSWORD]
            )
            KlimaPasswordField(
                value = state.form.confirmPassword,
                onValueChange = { onEvent(SignUpEvent.OnConfirmPasswordChanged(it)) },
                label = "Confirm Password",
                placeholder = "Confirm your password",
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                isError = !state.fieldErrors[FieldType.CONFIRM_PASSWORD].isNullOrEmpty(),
                errorMessage = state.fieldErrors[FieldType.CONFIRM_PASSWORD]
            )
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                onClick = { onEvent(SignUpEvent.OnSubmit) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Neptune
                )
            ) {
                Text(text = "SUBMIT", color = White)
            }
        }
    }
}