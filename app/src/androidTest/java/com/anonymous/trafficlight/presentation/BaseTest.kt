package com.anonymous.trafficlight.presentation

import androidx.activity.viewModels
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PixelMap
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.presentation.car_model.CarModelView
import com.anonymous.trafficlight.presentation.navigation.Screen
import com.anonymous.trafficlight.presentation.theme.AppTheme
import com.anonymous.trafficlight.presentation.traffic_light.TrafficLightView
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import java.util.*
import kotlin.concurrent.schedule

@HiltAndroidTest
open class BaseTest {

    @get:Rule(order = 1)
    internal val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    internal val composeTestRule = createAndroidComposeRule<MainActivity>()

    internal lateinit var navController: NavHostController

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

    /**
     * Extension function for compose rule to navigate from car model view to traffic light view
     *
     * @param carModel value of car model text
     */
    internal fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.navigateToTrafficLightScreen(
        carModel: String
    ) {
        val carModelText = onNodeWithTag(Constant.carModelTextField)
        val startDrivingButton = onNodeWithText(Constant.startDriving)

        carModelText.performTextInput(carModel)
        startDrivingButton.performClick()

        waitForIdle()
    }

    /**
     * Runs through the pixel of given view node and asserts if given tint is available
     *
     * @param tint - Color value for the background of view
     */
    fun SemanticsNodeInteraction.assertContainsColor(tint: Color): SemanticsNodeInteraction {
        val imageBitmap = captureToImage()
        val buffer = IntArray(imageBitmap.width * imageBitmap.height)
        imageBitmap.readPixels(buffer, 0, 0, imageBitmap.width - 1, imageBitmap.height - 1)
        val pixelColors = PixelMap(buffer, 0, 0, imageBitmap.width - 1, imageBitmap.height - 1)

        (0 until imageBitmap.width).forEach { x ->
            (0 until imageBitmap.height).forEach { y ->
                if (pixelColors[x, y] == tint) return this
            }
        }
        throw AssertionError("Assert failed: The component does not contain the color")
    }

    /**
     * Keep the compose testing idle until the delay
     *
     * @param composeTestRule Compose rule which runs the test
     * @param delay Delay in milliseconds required to delay the compose
     */
    fun asyncTimer(composeTestRule: ComposeTestRule, delay: Long = 1000) {
        AsyncTimer.start(delay)
        composeTestRule.waitUntil(
            condition = { AsyncTimer.expired },
            timeoutMillis = delay + 1000
        )

    }

    object AsyncTimer {
        var expired = false
        fun start(delay: Long = 1000) {
            expired = false
            Timer().schedule(delay) {
                expired = true
            }
        }
    }
}