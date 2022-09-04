package com.anonymous.trafficlight.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.presentation.theme.gray
import com.anonymous.trafficlight.presentation.theme.green
import com.anonymous.trafficlight.presentation.theme.orange
import com.anonymous.trafficlight.presentation.theme.red
import com.anonymous.trafficlight.presentation.traffic_light.TrafficLightState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val modelValidator: ModelValidator) : ViewModel() {

    private var mTrafficLightState = MutableLiveData<TrafficLightState>()
    val trafficLightState: LiveData<TrafficLightState> = mTrafficLightState
    private lateinit var mCarModel: String

    fun validateCarModel(carModel: String):String {
        mCarModel = carModel
        return modelValidator.validate(carModel)
    }

    fun getCarModel(): String = mCarModel

    fun getRedLightColor(lightState: TrafficLightState?): Color =
        if (lightState == TrafficLightState.RED) red else gray

    fun getGreenLightColor(lightState: TrafficLightState?): Color =
        if (lightState == TrafficLightState.GREEN) green else gray

    fun getOrangeLightColor(lightState: TrafficLightState?): Color =
        if (lightState == TrafficLightState.ORANGE) orange else gray

    suspend fun initTrafficLight() {
        withContext(Dispatchers.IO) {
            while (true) {
                mTrafficLightState.postValue(TrafficLightState.GREEN)
                delay(Constant.GREEN_LIGHT_DELAY)
                mTrafficLightState.postValue(TrafficLightState.ORANGE)
                delay(Constant.ORANGE_LIGHT_DELAY)
                mTrafficLightState.postValue(TrafficLightState.RED)
                delay(Constant.RED_LIGHT_DELAY)
            }
        }
    }

}