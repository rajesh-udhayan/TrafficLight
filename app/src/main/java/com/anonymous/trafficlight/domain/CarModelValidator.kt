package com.anonymous.trafficlight.domain

import com.anonymous.trafficlight.commons.Constant
import com.anonymous.trafficlight.presentation.ModelValidator
import javax.inject.Inject

class CarModelValidator @Inject constructor(): ModelValidator{

    override fun validate(model: String): String {
        if (model.isEmpty()) {
            return Constant.emptyCarModel
        } else {
            if (model.length < Constant.MIN_CHAR_LENGTH) {
                return Constant.invalidCarModel
            }
            return Constant.success
        }
    }
}
