package com.lacolinares.klima.presensation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lacolinares.klima.presensation.theme.Black
import com.lacolinares.klima.presensation.theme.Gray
import com.lacolinares.klima.presensation.theme.Mirage
import com.lacolinares.klima.presensation.theme.Neptune
import com.lacolinares.klima.presensation.theme.White

@Composable
fun KlimaTextField(
    value: String = "",
    onValueChange: (String) -> Unit,
    label: String = "",
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
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
        keyboardOptions = keyboardOptions,
        singleLine = true,
        colors = textFieldColors
    )
}