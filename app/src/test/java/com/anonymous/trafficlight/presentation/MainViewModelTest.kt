package com.anonymous.trafficlight.presentation

import com.anonymous.trafficlight.commons.Constant.success
import com.anonymous.trafficlight.domain.CarModelValidator
import com.anonymous.trafficlight.presentation.theme.gray
import com.anonymous.trafficlight.presentation.theme.green
import com.anonymous.trafficlight.presentation.theme.orange
import com.anonymous.trafficlight.presentation.theme.red
import com.anonymous.trafficlight.presentation.traffic_light.TrafficLightState
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var validator: CarModelValidator

    @Before
    fun setUp() {
        validator = mockk()
        viewModel = MainViewModel(validator)

        every { validator.validate("test") } returns success
    }

    @Test
    fun `should invoke validate method in validator when view model method called`() {
        val result = viewModel.validateCarModel("test")

        verify { validator.validate("test") }
        assertThat(result).isEqualTo(success)
    }

    @Test
    fun `should return gray for red light when traffic light state is not red`(){
        val result = viewModel.getRedLightColor(TrafficLightState.ORANGE)

        assertThat(result).isEqualTo(gray)
    }

    @Test
    fun `should return red for red light when traffic light state is red`(){
        val result = viewModel.getRedLightColor(TrafficLightState.RED)

        assertThat(result).isEqualTo(red)
    }

    @Test
    fun `should return gray for green light when traffic light state is not green`(){
        val result = viewModel.getGreenLightColor(TrafficLightState.RED)

        assertThat(result).isEqualTo(gray)
    }

    @Test
    fun `should return green for green light when traffic light state is green`(){
        val result = viewModel.getGreenLightColor(TrafficLightState.GREEN)

        assertThat(result).isEqualTo(green)
    }

    @Test
    fun `should return gray for orange light when traffic light state is not orange`(){
        val result = viewModel.getOrangeLightColor(TrafficLightState.GREEN)

        assertThat(result).isEqualTo(gray)
    }

    @Test
    fun `should return orange for orange light when traffic light state is orange`(){
        val result = viewModel.getOrangeLightColor(TrafficLightState.ORANGE)

        assertThat(result).isEqualTo(orange)
    }

    @Test
    fun `should return correct car model when validate car model invoked`(){
        val actualValue = "test"
        viewModel.validateCarModel(actualValue)
        val result = viewModel.getCarModel()

        assertThat(result).isEqualTo(actualValue)
    }
}