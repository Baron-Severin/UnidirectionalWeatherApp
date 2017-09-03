package com.foo.umbrella.weather.models

import com.foo.umbrella.dataFlow.State

data class ForecastHourModel(val temperature: String, val icon: String, val hour: String, val color: State.Color)
