package com.anonymous.trafficlight.domain

import com.anonymous.trafficlight.commons.Constant

class CarModelValidator {

    fun validate(model: String): String {
        if (model.isEmpty()) {
            return Constant.emptyCarModel
        } else {
            if (model.length < 3) {
                return Constant.invalidCarModel
            }
            return Constant.success
        }
    }
}
