package com.anonymous.trafficlight.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val modelValidator: ModelValidator) : ViewModel() {

    fun validateCarModel(carModel: String) = modelValidator.validate(carModel)
}