package com.anonymous.trafficlight.presentation.car_model

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.commons.Constant.enterCarModel
import com.anonymous.trafficlight.presentation.theme.Blue400

@Composable
fun CarModelView() {
    var carModel by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 56.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = Constant.screenTitle,
                color = Blue400,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TextField(modifier = Modifier
                .fillMaxWidth()
                .testTag(Constant.carModelTextField),
                value = carModel,
                onValueChange = {
                    carModel = it
                },
                label = { Text(enterCarModel)}
            )
            Spacer(Modifier.size(16.dp))
            Button(
                onClick = {

                },
                modifier = Modifier
                    .padding(all = 8.dp)
            ) {
                Text(text = Constant.startDriving)
            }
        }

    }
}