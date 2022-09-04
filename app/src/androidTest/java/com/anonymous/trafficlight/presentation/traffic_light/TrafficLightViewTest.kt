package com.anonymous.trafficlight.presentation.traffic_light

import androidx.activity.viewModels
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.anonymous.trafficlight.R
import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.presentation.MainActivity
import com.anonymous.trafficlight.presentation.MainViewModel
import com.anonymous.trafficlight.presentation.TrafficLightView
import com.anonymous.trafficlight.presentation.theme.*
import com.anonymous.trafficlight.utils.assertContainsColor
import com.anonymous.trafficlight.utils.asyncTimer
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
                val navController = rememberNavController()
                val viewModel = composeTestRule.activity.viewModels<MainViewModel>().value
                TrafficLightView(viewModel,navController)
            }
        }
    }

    @Test
    fun shouldDisplayUIElements() {
        with(composeTestRule) {
            val title = onNodeWithText(activity.getString(R.string.app_name))
            val carModelText = onNodeWithTag(Constant.carModelText)
            val greenLight = onNodeWithTag(Constant.greenLightTag)
            val orangeLight = onNodeWithTag(Constant.orangeLightTag)
            val redLight = onNodeWithTag(Constant.redLightTag)

            title.assertIsDisplayed()
            carModelText.assertIsDisplayed()
            greenLight.assertIsDisplayed()
            orangeLight.assertIsDisplayed()
            redLight.assertIsDisplayed()
        }
    }

    @Test
    fun shouldDisplayRespectiveBackgroundColorForTrafficLights(){
        with(composeTestRule){
            val greenLight = onNodeWithTag(Constant.greenLightTag)
            val orangeLight = onNodeWithTag(Constant.orangeLightTag)
            val redLight = onNodeWithTag(Constant.redLightTag)

            greenLight.assertContainsColor(green)
            orangeLight.assertContainsColor(gray)
            redLight.assertContainsColor(gray)

            asyncTimer(this,4000)

            orangeLight.assertContainsColor(orange)
            greenLight.assertContainsColor(gray)
            redLight.assertContainsColor(gray)

            asyncTimer(this,1000)

            redLight.assertContainsColor(red)
            greenLight.assertContainsColor(gray)
            orangeLight.assertContainsColor(gray)

            asyncTimer(this,4000)

            greenLight.assertContainsColor(green)
            orangeLight.assertContainsColor(gray)
            redLight.assertContainsColor(gray)
        }
    }
}