package com.anonymous.trafficlight.presentation.traffic_light

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anonymous.trafficlight.R
import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.presentation.MainViewModel
import com.anonymous.trafficlight.presentation.theme.Red400
import com.anonymous.trafficlight.presentation.theme.Typography

@Composable
fun TrafficLightView(viewModel: MainViewModel, navController: NavController) {
    InitTrafficLight(viewModel)
    val trafficLightState by viewModel.trafficLightState.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
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
                text = stringResource(id = R.string.car_model, viewModel.getCarModel()),
                color = Red400,
                style = Typography.h2
            )
            Spacer(modifier = Modifier.size(16.dp))
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(36.dp)
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(64.dp)
                    )
                    .border(16.dp, Color.DarkGray, RoundedCornerShape(64.dp))
                    .padding(24.dp)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(100.dp)
                            .testTag(Constant.redLightTag)
                            .background(
                                color = viewModel.getRedLightColor(trafficLightState),
                                shape = CircleShape
                            )
                            .border(4.dp, Color.DarkGray, CircleShape)
                    )
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(100.dp)
                            .testTag(Constant.orangeLightTag)
                            .background(
                                color = viewModel.getOrangeLightColor(trafficLightState),
                                shape = CircleShape
                            )
                            .border(4.dp, Color.DarkGray, CircleShape)
                    )
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(100.dp)
                            .testTag(Constant.greenLightTag)
                            .background(
                                color = viewModel.getGreenLightColor(trafficLightState),
                                shape = CircleShape
                            )
                            .border(4.dp, Color.DarkGray, CircleShape)
                    )
                }
            }
        }
    }
}

@Composable
fun InitTrafficLight(viewModel: MainViewModel){
    LaunchedEffect(key1 = Unit, block = {
        try {
            viewModel.initTrafficLight()
        } catch (e: Exception){
            e.printStackTrace()
        }
    })
}