package com.foo.umbrella.dataFlow

import com.foo.umbrella.weather.models.CurrentConditionsVm
import com.foo.umbrella.weather.models.ForecastCardModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject


class Store(val reducer: Reducer, val stateSubject: BehaviorSubject<State>, val eventObservable: Observable<Event>) {
  init {
    eventObservable.subscribe {
      stateSubject.onNext(reducer.reduce(stateSubject.value, it))
    }
  }
}