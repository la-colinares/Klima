package com.lacolinares.klima.presensation.screens.signup

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.klima.presensation.composables.KlimaPasswordField
import com.lacolinares.klima.presensation.composables.KlimaTextField
import com.lacolinares.klima.presensation.theme.Mirage
import com.lacolinares.klima.presensation.theme.Neptune
import com.lacolinares.klima.presensation.theme.Silver
import com.lacolinares.klima.presensation.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onBack: () -> Unit = {},
    onSignUpSuccess: () -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
                value = fullName,
                onValueChange = { fullName = it },
                label = "Full Name",
                placeholder = "Enter your full name",
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
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
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            KlimaPasswordField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Confirm Password",
                placeholder = "Confirm your password",
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
                Text(text = "SUBMIT", color = White)
            }
        }
    }
}