package com.anonymous.trafficlight.di

import com.anonymous.trafficlight.domain.CarModelValidator
import com.anonymous.trafficlight.presentation.ModelValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModule {

    @Binds
    abstract fun bindNavigator(impl: CarModelValidator): ModelValidator
}