package com.lacolinares.klima.presensation.screens.login

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.klima.R
import com.lacolinares.klima.presensation.composables.KlimaPasswordField
import com.lacolinares.klima.presensation.composables.KlimaTextField
import com.lacolinares.klima.presensation.theme.Gray
import com.lacolinares.klima.presensation.theme.Mirage
import com.lacolinares.klima.presensation.theme.Neptune
import com.lacolinares.klima.presensation.theme.White

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    onSignUp: () -> Unit = {},
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                    value = username,
                    onValueChange = { username = it },
                    label = "Username",
                    placeholder = "Enter your username",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                KlimaPasswordField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    placeholder = "Enter your password",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    onClick = {},
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
                                    linkInteractionListener = { onSignUp.invoke() }
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
    }
}