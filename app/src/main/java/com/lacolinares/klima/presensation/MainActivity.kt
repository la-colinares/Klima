package com.lacolinares.klima.presensation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.lacolinares.klima.presensation.navigation.AppNavigation
import com.lacolinares.klima.presensation.screens.login.LoginScreen
import com.lacolinares.klima.presensation.screens.login.state.LoginUiState
import com.lacolinares.klima.presensation.screens.main.home.HomeScreen
import com.lacolinares.klima.presensation.screens.main.home.HomeUiState
import com.lacolinares.klima.presensation.screens.signup.SignUpScreen
import com.lacolinares.klima.presensation.screens.signup.state.SignUpUiState
import com.lacolinares.klima.presensation.theme.KlimaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KlimaTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen(
        state = LoginUiState(),
        onEvent = {}
    )
}

@Preview
@Composable
fun SignUpScreenPreview(){
    SignUpScreen(
        state = SignUpUiState(),
        onEvent = {}
    )
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(
        state = HomeUiState(),
        onGetWeather = {}
    )
}