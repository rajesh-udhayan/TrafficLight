package com.anonymous.trafficlight.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CarModelValidatorTest {

    @Test
    fun `should return success message when car model is valid`(){
        val validator = CarModelValidator()
        val actual = validator.validate("test")

        assertThat(actual).isEqualTo("success")
    }

    @Test
    fun `should return empty message when car model is empty`(){
        val validator = CarModelValidator()
        val actual = validator.validate("")

        assertThat(actual).isEqualTo("Please enter your car model")
    }
}