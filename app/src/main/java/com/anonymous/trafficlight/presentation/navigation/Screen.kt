package com.anonymous.trafficlight.presentation.navigation

sealed class Screen(val route: String) {
    object CarModelScreen : Screen("car_model_screen")
    object TrafficLightScreen : Screen("traffic_light_screen")

}
