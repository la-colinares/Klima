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

}