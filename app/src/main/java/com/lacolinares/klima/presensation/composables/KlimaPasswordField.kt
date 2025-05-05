package com.lacolinares.klima.presensation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.lacolinares.klima.R
import com.lacolinares.klima.presensation.theme.Black
import com.lacolinares.klima.presensation.theme.Gray
import com.lacolinares.klima.presensation.theme.Mirage
import com.lacolinares.klima.presensation.theme.Neptune
import com.lacolinares.klima.presensation.theme.White

@Composable
fun KlimaPasswordField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    label: String = "",
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
){
    var isPasswordVisible by remember { mutableStateOf(false) }

    val textFieldColors = TextFieldDefaults.colors(
        unfocusedContainerColor = White,
        focusedContainerColor = White,

        unfocusedLabelColor = Gray,
        focusedLabelColor = Mirage,

        unfocusedTextColor = Black,
        focusedTextColor = Black,

        cursorColor = Mirage,

        focusedIndicatorColor = Neptune
    )

    TextField(
        value = value,
        onValueChange = { onValueChange.invoke(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        trailingIcon = {
            IconButton(
                onClick = {
                    isPasswordVisible = !isPasswordVisible
                }
            ) {
                val iconId = if (isPasswordVisible) R.drawable.visibility_24px else R.drawable.visibility_off_24px
                val description = if (isPasswordVisible) "Password Shown" else "Password Hidden"
                Icon(
                    painter = painterResource(iconId),
                    tint = Gray,
                    contentDescription = description
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = keyboardOptions,
        singleLine = true,
        colors = textFieldColors
    )
}