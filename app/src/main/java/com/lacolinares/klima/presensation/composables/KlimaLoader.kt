package com.lacolinares.klima.presensation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.lacolinares.klima.presensation.theme.Neptune

@Composable
fun KlimaLoader() {
    Dialog(onDismissRequest = {}) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.wrapContentSize()
        ) {
            CircularProgressIndicator(color = Neptune)
        }
    }
}