package com.foo.umbrella.weather

import com.foo.umbrella.data.api.WeatherService
import com.foo.umbrella.dataFlow.RequestFailedEvent
import com.foo.umbrella.dataFlow.Event
import com.foo.umbrella.dataFlow.WeatherResponseEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.Subject

class WeatherDispatcher(private val weatherService: WeatherService,
                        private val eventSubject: Subject<Event>,
                        var zip: Int) {

  fun requestUpdate() {
    weatherService.forecastForZipObservable(zip.toString())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
          if (it.isError || it.response()?.body() == null) {
            RequestFailedEvent(it.error().toString())
          } else {
            WeatherResponseEvent(it.response()!!.body()!!)
          }
        }
        .singleElement()
        .subscribe{ eventSubject.onNext(it) }
  }

}

