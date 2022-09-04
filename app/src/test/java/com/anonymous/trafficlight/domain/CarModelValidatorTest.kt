package com.anonymous.trafficlight.domain

import com.anonymous.trafficlight.commons.Constant
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class CarModelValidatorTest {

    private lateinit var validator: CarModelValidator

    @Before
    fun setUp(){
        validator = CarModelValidator()
    }

    @Test
    fun `should return success message when car model is valid`(){
        val actual = validator.validate("test")

        assertThat(actual).isEqualTo(Constant.success)
    }

    @Test
    fun `should return empty message when car model is empty`(){
        val actual = validator.validate("")

        assertThat(actual).isEqualTo(Constant.emptyCarModel)
    }
}