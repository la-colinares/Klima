package com.lacolinares.klima.presensation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.lacolinares.klima.presensation.screens.login.LoginScreen
import com.lacolinares.klima.presensation.screens.login.LoginViewModel
import com.lacolinares.klima.presensation.screens.main.MainScreen
import com.lacolinares.klima.presensation.screens.main.MainScreenViewModel
import com.lacolinares.klima.presensation.screens.signup.SignUpScreen
import com.lacolinares.klima.presensation.screens.signup.SignUpViewModel
import org.koin.androidx.compose.koinViewModel

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
                val viewModel: LoginViewModel = koinViewModel()
                val state by viewModel.state.collectAsStateWithLifecycle()

                LoginScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onSignUp = {
                        navController.navigate(Routes.Auth.SignUp)
                    },
                    onLoginSuccess = {
                        navController.navigate(Routes.Main.Graph) {
                            popUpTo(Routes.Auth.Login) { inclusive = true }
                        }
                    }
                )
            }

            composable<Routes.Auth.SignUp> {
                val viewModel: SignUpViewModel = koinViewModel()
                val state by viewModel.state.collectAsStateWithLifecycle()

                SignUpScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onBack = {
                        navController.navigateUp()
                    },
                    onSignUpSuccess = {
                        navController.navigateUp()
                    }
                )
            }
        }
        composable<Routes.Main.Graph> {
            val viewModel: MainScreenViewModel = koinViewModel()
            MainScreen(
                onLogout = {
                    viewModel.onLogout()
                    navController.navigate(Routes.Auth.Graph){
                        popUpTo(Routes.Main.Graph) { inclusive = true }
                    }
                }
            )
        }
    }
}