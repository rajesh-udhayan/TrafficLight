package com.anonymous.trafficlight.presentation.car_model

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.anonymous.trafficlight.commons.Constant
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CarModelViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CarModelView()
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

}