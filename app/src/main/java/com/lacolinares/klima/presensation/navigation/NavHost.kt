package com.lacolinares.klima.presensation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.lacolinares.klima.presensation.screens.login.LoginScreen
import com.lacolinares.klima.presensation.screens.signup.SignUpScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Auth.Graph
    ) {
        navigation<Routes.Auth.Graph>(
            startDestination = Routes.Auth.Login
        ) {
            composable<Routes.Auth.Login> {
                LoginScreen(
                    onSignUp = {
                        navController.navigate(Routes.Auth.SignUp)
                    },
                    onLoginSuccess = {

                    }
                )
            }

            composable<Routes.Auth.SignUp> {
                SignUpScreen(
                    onBack = {
                        navController.navigateUp()
                    },
                    onSignUpSuccess = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}