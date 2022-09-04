package com.anonymous.trafficlight.presentation.car_model

import androidx.activity.viewModels
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.presentation.MainActivity
import com.anonymous.trafficlight.presentation.MainViewModel
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

    @Before
    fun setUp() {
        hiltTestRule.inject()
        composeTestRule.setContent {
            val viewModel = composeTestRule.activity.viewModels<MainViewModel>().value
            CarModelView(viewModel)
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
}