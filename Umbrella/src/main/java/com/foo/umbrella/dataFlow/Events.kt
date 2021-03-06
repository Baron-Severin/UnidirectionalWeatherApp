package com.foo.umbrella.dataFlow

import com.foo.umbrella.data.model.WeatherData

abstract class Event

data class RequestFailedEvent(val message: String) : Event()
data class WeatherResponseEvent(val response: WeatherData) : Event()
data class SetUnitEvent(val newUnit: State.TemperatureUnit) : Event()
data class SetZipEvent(val newZip: String) : Event()
