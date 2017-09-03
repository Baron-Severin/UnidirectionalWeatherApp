package com.foo.umbrella.dataFlow

import com.foo.umbrella.data.model.CurrentObservation
import com.foo.umbrella.data.model.ForecastCondition
import com.foo.umbrella.weather.models.CurrentConditionsVm
import com.foo.umbrella.weather.models.ForecastCardModel
import com.foo.umbrella.weather.models.ForecastHourModel
import org.threeten.bp.LocalDateTime

class Reducer {
  fun reduce(state: State, event: Event) : State {
    when (event) {
      is WeatherResponseEvent -> return updateWeatherFromResponse(state, event)
      else -> throw RuntimeException()//TODO: flesh this out
    }
  }

  private fun updateWeatherFromResponse(state: State, event: WeatherResponseEvent) : State {
    val currentConditions = currentConditionsVmFromResponse(event.response.currentObservation, state.settings.unit)
    val forecastModels = forecastModelsFromResponse(event.response.forecast, state.settings.unit)

    return state.copy(currentConditionsVm = currentConditions, cards = forecastModels)
  }

  private fun currentConditionsVmFromResponse(observation: CurrentObservation, unit: State.TemperatureUnit) : CurrentConditionsVm {
    val description = observation.weatherDescription
    val temperature = if (unit == State.TemperatureUnit.FAHRENHEIT) observation.tempFahrenheit else observation.tempCelsius
    val displayTemp = Math.round(temperature.toDouble()).toString() + "°"
    val location = observation.displayLocation
    val fahrenheitInt = observation.tempFahrenheit.toFloat()
    val background = if (fahrenheitInt >= 60) 0 else 1
    return CurrentConditionsVm(displayTemp, description, location.fullName, background)
  }

  private fun forecastModelsFromResponse(forecasts: List<ForecastCondition>, unit: State.TemperatureUnit) : List<ForecastCardModel> {
    return forecasts.groupBy { it.dateTime.dayOfYear }
        .values
        .sortedWith(compareBy({it[0].dateTime.year}, {it[0].dateTime.dayOfYear}))
        .map { cardModelFromOneDay(it, unit) }
  }

  private fun cardModelFromOneDay(day : List<ForecastCondition>, unit: State.TemperatureUnit) : ForecastCardModel {
    val currentDay = LocalDateTime.now().dayOfMonth
    val forecastDay = day[0].dateTime.dayOfMonth
    val name = when {
      forecastDay == currentDay -> "Today"
      forecastDay - currentDay == 1 -> "Tomorrow"
      else -> day[0].dateTime.dayOfWeek.name.toLowerCase().capitalize()
    }
    val hours = mutableListOf<ForecastHourModel>()
    day.forEachIndexed {index, hourForecast ->
      val temperature = if (unit == State.TemperatureUnit.FAHRENHEIT)
        hourForecast.tempFahrenheit
      else hourForecast.tempCelsius
      val baseHour = if (hourForecast.displayTime[1] == ':') hourForecast.displayTime.substring(0, 1)
                    else hourForecast.displayTime.substring(0, 2)
      val hour = when {
        baseHour == "0" -> "Midnight"
        baseHour == "12" -> "Noon"
        baseHour.toInt() < 12 -> "$baseHour:00 AM"
        else -> "$baseHour:00 PM"
      }
      val baseIcon = hourForecast.icon
      val correctedIcon = when (baseIcon) {
        "clear" -> "sunny"
        else -> baseIcon
      }
      val icon = "weather_${correctedIcon}"

      // TODO: map icon string to drawable
      hours.add(ForecastHourModel(temperature, icon, hour))
    }
    return ForecastCardModel(name, hours)
  }

}