package com.anonymous.trafficlight.presentation.car_model

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.commons.Constant.enterCarModel
import com.anonymous.trafficlight.presentation.MainViewModel
import com.anonymous.trafficlight.presentation.navigation.Screen
import com.anonymous.trafficlight.presentation.theme.Blue400
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CarModelView(viewModel: MainViewModel, navController: NavController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    var carModel by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
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
                    val msg = viewModel.validateCarModel(carModel.text)
                    if (msg.equals(Constant.success)) {
                        navController.navigate(Screen.TrafficLightScreen.route)
                    } else {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = msg
                            )
                        }
                    }
                },
                modifier = Modifier
                    .padding(all = 8.dp)
            ) {
                Text(text = Constant.startDriving)
            }
        }

    }
}