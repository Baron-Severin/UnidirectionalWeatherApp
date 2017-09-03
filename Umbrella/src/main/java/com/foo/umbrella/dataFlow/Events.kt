package com.foo.umbrella.dataFlow

import com.foo.umbrella.data.model.WeatherData

abstract class Event

data class RequestFailedEvent(val message: String) : Event()
data class WeatherResponseEvent(val response: WeatherData) : Event()
