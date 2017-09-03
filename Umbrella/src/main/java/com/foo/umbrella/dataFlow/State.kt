package com.foo.umbrella.dataFlow

import com.foo.umbrella.weather.models.CurrentConditionsVm
import com.foo.umbrella.weather.models.ForecastCardModel

data class State(val settings: Settings,
                 val currentConditionsVm: CurrentConditionsVm,
                 val cards: List<ForecastCardModel>) {

  data class Settings(val unit: TemperatureUnit,
                      val zip: Int)
  enum class TemperatureUnit {
    FAHRENHEIT, CELCIUS
  }

}
