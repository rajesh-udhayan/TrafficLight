package com.anonymous.trafficlight.presentation.traffic_light

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.anonymous.trafficlight.R
import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.presentation.MainActivity
import com.anonymous.trafficlight.presentation.TrafficLightView
import com.anonymous.trafficlight.presentation.theme.AppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TrafficLightViewTest {

    @get:Rule(order = 1)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltTestRule.inject()
        composeTestRule.setContent {
            AppTheme {
                TrafficLightView()
            }
        }
    }

    @Test
    fun shouldDisplayUIElements() {
        with(composeTestRule) {
            val title = onNodeWithText(activity.getString(R.string.app_name))
            val carModelText = onNodeWithTag(Constant.carModelText)

            title.assertIsDisplayed()
            carModelText.assertIsDisplayed()
        }
    }
}