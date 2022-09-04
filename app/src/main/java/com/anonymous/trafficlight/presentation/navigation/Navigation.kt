package com.anonymous.trafficlight.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anonymous.trafficlight.presentation.MainViewModel
import com.anonymous.trafficlight.presentation.TrafficLightView
import com.anonymous.trafficlight.presentation.car_model.CarModelView

@Composable
fun Navigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.CarModelScreen.route) {
        composable(route = Screen.CarModelScreen.route) {
            CarModelView(viewModel = viewModel, navController)
        }
        composable(
            route = Screen.TrafficLightScreen.route
        ) {
            TrafficLightView(viewModel = viewModel, navController)
        }
    }
}