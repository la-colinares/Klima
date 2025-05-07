package com.lacolinares.klima.presensation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lacolinares.klima.presensation.screens.main.home.HomeScreen
import com.lacolinares.klima.presensation.screens.main.home.HomeViewModel
import com.lacolinares.klima.presensation.screens.main.weather.WeatherListScreen
import com.lacolinares.klima.presensation.screens.main.weather.WeatherListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BottomNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Main.BottomNavScreen.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = Routes.Main.BottomNavScreen.Home.route) {
            val viewModel: HomeViewModel = koinViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            HomeScreen(
                state = state,
                onGetWeather = { location ->
                    viewModel.fetchWeather(lat = location.latitude, lon = location.longitude)
                }
            )
        }

        composable(route = Routes.Main.BottomNavScreen.Weather.route) {
            val viewModel: WeatherListViewModel = koinViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            WeatherListScreen(
                state = state,
                onLoadWeathers = { viewModel.loadWeathers() }
            )
        }
    }
}