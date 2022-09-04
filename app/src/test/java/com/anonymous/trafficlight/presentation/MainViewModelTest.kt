package com.anonymous.trafficlight.presentation

import com.anonymous.trafficlight.commons.Constant.success
import com.anonymous.trafficlight.domain.CarModelValidator
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class MainViewModelTest {

    @Test
    fun `should invoke validate method in validator when view model method called`() {
        val carModelValidator: CarModelValidator = mockk()
        val viewModel = MainViewModel(carModelValidator)

        every { carModelValidator.validate("test") } returns success
        val result = viewModel.validateCarModel("test")

        verify { carModelValidator.validate("test") }
        assertThat(result).isEqualTo(success)
    }
}