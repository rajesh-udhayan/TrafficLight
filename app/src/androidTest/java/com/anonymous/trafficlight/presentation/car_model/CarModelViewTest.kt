package com.anonymous.trafficlight.presentation.car_model

import android.support.test.uiautomator.UiDevice
import androidx.activity.viewModels
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.anonymous.trafficlight.R
import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.presentation.MainActivity
import com.anonymous.trafficlight.presentation.MainViewModel
import com.anonymous.trafficlight.presentation.traffic_light.TrafficLightView
import com.anonymous.trafficlight.presentation.navigation.Screen
import com.anonymous.trafficlight.presentation.theme.AppTheme
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CarModelViewTest {

    @get:Rule(order = 1)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: NavHostController

    @Before
    fun setUp() {
        hiltTestRule.inject()
        composeTestRule.setContent {
            AppTheme {
                val viewModel = composeTestRule.activity.viewModels<MainViewModel>().value
                navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.CarModelScreen.route
                ) {
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
        }
    }

    @Test
    fun shouldDisplayAllUIElements() {
        with(composeTestRule) {
            val title = onNodeWithText(Constant.screenTitle)
            val carModelTextField = onNodeWithTag(Constant.carModelTextField)
            val startDrivingButton = onNodeWithText(Constant.startDriving)

            title.assertIsDisplayed()
            carModelTextField.assertIsDisplayed()
            startDrivingButton.assertIsDisplayed()
        }
    }

    @Test
    fun shouldDisplayEmptySnackBarMessage() {
        with(composeTestRule) {
            val carModelTextField = onNodeWithTag(Constant.carModelTextField)
            val startDrivingButton = onNodeWithText(Constant.startDriving)
            val emptyCarModelMsg = onNodeWithText(Constant.emptyCarModel)

            carModelTextField.performTextInput("")
            startDrivingButton.performClick()
            emptyCarModelMsg.assertIsDisplayed()
        }
    }

    @Test
    fun shouldDisplayInvalidSnackBarMessage() {
        with(composeTestRule) {
            val carModelTextField = onNodeWithTag(Constant.carModelTextField)
            val startDrivingButton = onNodeWithText(Constant.startDriving)
            val invalidCarModelMsg = onNodeWithText(Constant.invalidCarModel)

            carModelTextField.performTextInput("ab")
            startDrivingButton.performClick()

            invalidCarModelMsg.assertIsDisplayed()
        }
    }

    @Test
    fun shouldNavigateToTrafficLightScreenWhenCarModelIsValid() {
        with(composeTestRule) {
            navigateToTrafficLightScreen("test")

            val route = navController.currentBackStackEntry?.destination?.route
            Truth.assertThat(route).isEqualTo(Screen.TrafficLightScreen.route)
        }
    }

    @Test
    fun shouldNavigateToHomeScreenWhenBackPressedOnTrafficLightScreen() {
        with(composeTestRule) {
            navigateToTrafficLightScreen("test")

            val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            mDevice.pressBack()
            val route = navController.currentBackStackEntry?.destination?.route
            Truth.assertThat(route).isEqualTo(Screen.CarModelScreen.route)
        }
    }

    @Test
    fun shouldDisplayCarModelTextInTrafficLightScreenPassedFromCarModelScreen(){
        with(composeTestRule){
            val carModel = "ford"
            navigateToTrafficLightScreen(carModel)
            val carModelTextView = onNodeWithText(composeTestRule.activity.getString(R.string.car_model, carModel))

            waitForIdle()
            carModelTextView.assertIsDisplayed()
        }
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.navigateToTrafficLightScreen(carModel: String) {
        val carModelText = onNodeWithTag(Constant.carModelTextField)
        val startDrivingButton = onNodeWithText(Constant.startDriving)

        carModelText.performTextInput(carModel)
        startDrivingButton.performClick()

        waitForIdle()
    }
}