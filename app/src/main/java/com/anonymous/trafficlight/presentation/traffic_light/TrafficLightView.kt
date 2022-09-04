package com.anonymous.trafficlight.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anonymous.trafficlight.R
import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.presentation.theme.Red400
import com.anonymous.trafficlight.presentation.theme.gray

@Composable
fun TrafficLightView() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp),
        ) {
            Text(
                modifier = Modifier.testTag(Constant.carModelText),
                text = stringResource(id = R.string.car_model, ""),
                color = Red400
            )
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(100.dp)
                    .testTag(Constant.greenLightTag)
                    .background(
                        color = gray,
                        shape = CircleShape
                    )
            )
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(100.dp)
                    .testTag(Constant.orangeLightTag)
                    .background(
                        color = gray,
                        shape = CircleShape
                    )
            )
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(100.dp)
                    .testTag(Constant.redLightTag)
                    .background(
                        color = gray,
                        shape = CircleShape
                    )
            )
        }
    }
}