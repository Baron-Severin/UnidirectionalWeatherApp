package com.foo.umbrella.settings

import com.foo.umbrella.dataFlow.Event
import com.foo.umbrella.dataFlow.SetUnitEvent
import com.foo.umbrella.dataFlow.State
import io.reactivex.subjects.Subject

class SettingsDispatcher(private val eventSubject: Subject<Event>) {

  fun onUnitClicked(setTo: State.TemperatureUnit) {
    eventSubject.onNext(SetUnitEvent(setTo))
  }



}