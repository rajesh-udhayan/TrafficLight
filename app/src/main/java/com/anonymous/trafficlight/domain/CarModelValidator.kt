package com.anonymous.trafficlight.domain

import com.anonymous.trafficlight.commons.Constant

class CarModelValidator {

    fun validate(model: String): String{
        return if (model.isEmpty()) {
            Constant.emptyCarModel
        } else {
            Constant.success
        }
    }
}
