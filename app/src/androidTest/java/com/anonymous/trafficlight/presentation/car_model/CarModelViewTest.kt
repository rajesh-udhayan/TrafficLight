package com.anonymous.trafficlight.presentation.car_model

import android.support.test.uiautomator.UiDevice
import androidx.compose.ui.test.*
import androidx.test.platform.app.InstrumentationRegistry
import com.anonymous.trafficlight.R
import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.presentation.BaseTest
import com.anonymous.trafficlight.presentation.navigation.Screen
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class CarModelViewTest() : BaseTest() {

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
    fun shouldDisplayCarModelTextInTrafficLightScreenPassedFromCarModelScreen() {
        with(composeTestRule) {
            val carModel = "ford"
            navigateToTrafficLightScreen(carModel)
            val carModelTextView =
                onNodeWithText(composeTestRule.activity.getString(R.string.car_model, carModel))

            waitForIdle()
            carModelTextView.assertIsDisplayed()
        }
    }

}