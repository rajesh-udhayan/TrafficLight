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
import com.anonymous.trafficlight.commons.Constant

@Composable
fun CarModelView() {
    var carModel by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold {
        Column {
            Text(text = Constant.screenTitle)

            TextField(modifier = Modifier
                .fillMaxWidth()
                .testTag(Constant.carModelTextField),
                value = carModel,
                onValueChange = {
                    carModel = it
                })

            Button(
                onClick = {

                }
            ) {
                Text(text = Constant.startDriving)
            }
        }

    }
}