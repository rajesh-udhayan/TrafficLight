package com.anonymous.trafficlight.presentation.car_model

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun CarModelView() {
    var carModel by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold {
        Column {
            Text(text = "Traffic Light")
            TextField(modifier = Modifier
                .fillMaxWidth()
                .testTag("car_model_text_field"),
                value = carModel,
                onValueChange = {
                    carModel = it
                })
            Button(
                onClick = {

                }
            ) {
                Text(text = "Start Driving")
            }
        }

    }
}