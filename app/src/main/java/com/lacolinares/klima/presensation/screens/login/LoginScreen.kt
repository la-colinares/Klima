package com.lacolinares.klima.presensation.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lacolinares.klima.R
import com.lacolinares.klima.domain.validation.FieldType
import com.lacolinares.klima.presensation.composables.KlimaPasswordField
import com.lacolinares.klima.presensation.composables.KlimaTextField
import com.lacolinares.klima.presensation.screens.login.state.LoginEvent
import com.lacolinares.klima.presensation.screens.login.state.LoginUiState
import com.lacolinares.klima.presensation.theme.Gray
import com.lacolinares.klima.presensation.theme.Mirage
import com.lacolinares.klima.presensation.theme.Neptune
import com.lacolinares.klima.presensation.theme.White

@Composable
fun LoginScreen(
    state: LoginUiState,
    onEvent: (LoginEvent) -> Unit,
    onLoginSuccess: () -> Unit = {},
    onSignUp: () -> Unit = {},
) {

    val context = LocalContext.current

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            onLoginSuccess.invoke()
        }
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            onEvent.invoke(LoginEvent.OnClearErrorMessage)
        }
    }

    LaunchedEffect(Unit) {
        onEvent.invoke(LoginEvent.OnValidateUserAuth)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Mirage
    ) { innerPadding ->
        val padding = PaddingValues(
            start = 24.dp,
            top = innerPadding.calculateTopPadding(),
            end = 24.dp,
            bottom = innerPadding.calculateBottomPadding()
        )
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    color = Neptune,
                    fontSize = 40.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                KlimaTextField(
                    value = state.email,
                    onValueChange = { onEvent.invoke(LoginEvent.OnEmailChanged(it)) },
                    label = "Email",
                    placeholder = "Enter your email",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    isError = !state.fieldErrors[FieldType.EMAIL].isNullOrEmpty(),
                    errorMessage = state.fieldErrors[FieldType.EMAIL]
                )
                KlimaPasswordField(
                    value = state.password,
                    onValueChange = { onEvent.invoke(LoginEvent.OnPasswordChanged(it)) },
                    label = "Password",
                    placeholder = "Enter your password",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    isError = !state.fieldErrors[FieldType.PASSWORD].isNullOrEmpty(),
                    errorMessage = state.fieldErrors[FieldType.PASSWORD]
                )
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    onClick = { onEvent.invoke(LoginEvent.OnLogin) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Neptune
                    )
                ) {
                    Text(text = "LOGIN", color = White)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Don't have an account? ")
                            withLink(
                                link = LinkAnnotation.Clickable(
                                    tag = "link_signup",
                                    styles = TextLinkStyles(style = SpanStyle(color = Neptune)),
                                    linkInteractionListener = {
                                        onEvent.invoke(LoginEvent.OnClearState)
                                        onSignUp.invoke()
                                    }
                                )
                            ) {
                                append("Signup")
                            }
                        },
                        color = Gray
                    )
                }
            }
        }

        state.authenticated?.let {
            if (it) onLoginSuccess.invoke()
        } ?: run {
            Dialog(onDismissRequest = {}) {
                Box(
                    contentAlignment= Alignment.Center,
                    modifier = Modifier.wrapContentSize()
                ){
                    CircularProgressIndicator(color = Neptune)
                }
            }
        }
    }
}