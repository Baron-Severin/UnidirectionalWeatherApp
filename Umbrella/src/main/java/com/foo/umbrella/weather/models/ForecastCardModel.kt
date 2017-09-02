package com.foo.umbrella.weather.models

data class ForecastCardModel(val index: Int, val name: String, val hourModels: List<ForecastHourModel>)
