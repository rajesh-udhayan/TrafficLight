package com.anonymous.trafficlight.presentation.car_model

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.presentation.MainActivity
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