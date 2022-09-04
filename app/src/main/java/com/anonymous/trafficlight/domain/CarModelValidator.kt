package com.anonymous.trafficlight.domain

class CarModelValidator {

    fun validate(model: String): String{
        return if (model.isEmpty()) {
            "Please enter your car model"
        } else {
            "success"
        }
    }
}
