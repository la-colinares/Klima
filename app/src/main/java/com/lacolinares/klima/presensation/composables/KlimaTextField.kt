package com.lacolinares.klima.presensation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lacolinares.klima.presensation.theme.Black
import com.lacolinares.klima.presensation.theme.Gray
import com.lacolinares.klima.presensation.theme.Mirage
import com.lacolinares.klima.presensation.theme.Neptune
import com.lacolinares.klima.presensation.theme.PersianRed
import com.lacolinares.klima.presensation.theme.White

@Composable
fun KlimaTextField(
    value: String = "",
    onValueChange: (String) -> Unit,
    label: String = "",
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    val textFieldColors = TextFieldDefaults.colors(
        unfocusedContainerColor = White,
        focusedContainerColor = White,

        unfocusedLabelColor = Gray,
        focusedLabelColor = Mirage,

        unfocusedTextColor = Black,
        focusedTextColor = Black,

        errorContainerColor = White,
        errorLabelColor = PersianRed,
        errorTextColor = Black,

        cursorColor = Mirage,

        focusedIndicatorColor = Neptune
    )
    Column {
        TextField(
            value = value,
            onValueChange = { onValueChange.invoke(it) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            keyboardOptions = keyboardOptions,
            singleLine = true,
            colors = textFieldColors
        )

        if (isError && !errorMessage.isNullOrEmpty()) {
            Column {
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = errorMessage, color = PersianRed)
                }
            }
        }
    }
}