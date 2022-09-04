package com.anonymous.trafficlight.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.anonymous.trafficlight.presentation.theme.gray
import com.anonymous.trafficlight.presentation.theme.red
import com.anonymous.trafficlight.presentation.traffic_light.TrafficLightState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val modelValidator: ModelValidator) : ViewModel() {

    fun validateCarModel(carModel: String) = modelValidator.validate(carModel)

    fun getRedLightColor(lightState: TrafficLightState?): Color =
        if (lightState == TrafficLightState.RED) red else gray

}