package com.anonymous.trafficlight.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PixelMap
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.ComposeTestRule
import java.util.*
import kotlin.concurrent.schedule

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

fun asyncTimer (composeTestRule: ComposeTestRule, delay: Long = 1000) {
    AsyncTimer.start (delay)
    composeTestRule.waitUntil (
        condition = {AsyncTimer.expired},
        timeoutMillis = delay + 1000
    )
}

object AsyncTimer {
    var expired = false
    fun start(delay: Long = 1000){
        expired = false
        Timer().schedule(delay) {
            expired = true
        }
    }
}