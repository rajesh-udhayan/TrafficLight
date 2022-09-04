package com.anonymous.trafficlight.presentation.car_model

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
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
    fun shouldDisplayScreenTitle() {
        with(composeTestRule) {
            val title = onNodeWithText("Traffic Light")

            title.assertIsDisplayed()
        }
    }

    @Test
    fun shouldDisplayCarModelTextField(){
        with(composeTestRule){
            val carModeltextField = onNodeWithTag("car_model_text_field")

            carModeltextField.assertIsDisplayed()
        }
    }

    @Test
    fun shouldDisplayStartDrivingButton(){
        with(composeTestRule){
            val startDrivingButton = onNodeWithText("Start Driving")

            startDrivingButton.assertIsDisplayed()
        }
    }
}