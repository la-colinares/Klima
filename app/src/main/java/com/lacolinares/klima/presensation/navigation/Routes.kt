package com.lacolinares.klima.presensation.navigation

import kotlinx.serialization.Serializable

object Routes {

    object Auth {
        @Serializable
        object Graph

        @Serializable
        object Login

        @Serializable
        object SignUp
    }

    object Main {
        @Serializable
        object Graph

        @Serializable
        sealed class BottomNavScreen(val route: String, val name: String) {
            @Serializable
            data object Home: BottomNavScreen("home_screen", "Home")
            @Serializable
            data object Weather: BottomNavScreen("weather_screen", "Weather")
        }

        @Serializable
        object Home { const val ROUTE = "home"}

        @Serializable
        object Weather { const val route = "weather"}

    }
}