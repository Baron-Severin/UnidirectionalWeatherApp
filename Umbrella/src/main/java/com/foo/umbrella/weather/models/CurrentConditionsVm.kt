package com.foo.umbrella.weather.models

import android.graphics.Color
import com.foo.umbrella.dataFlow.State

data class CurrentConditionsVm(val temperature: String, val description: String, val location: String, val color: Int)

