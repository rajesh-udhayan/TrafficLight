package com.anonymous.trafficlight.presentation.traffic_light

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.anonymous.trafficlight.R
import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.presentation.BaseTest
import com.anonymous.trafficlight.presentation.theme.gray
import com.anonymous.trafficlight.presentation.theme.green
import com.anonymous.trafficlight.presentation.theme.orange
import com.anonymous.trafficlight.presentation.theme.red
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class TrafficLightViewTest: BaseTest() {

    @Test
    fun shouldDisplayUIElements() {
        with(composeTestRule) {
            navigateToTrafficLightScreen("test")

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
            navigateToTrafficLightScreen("test")

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