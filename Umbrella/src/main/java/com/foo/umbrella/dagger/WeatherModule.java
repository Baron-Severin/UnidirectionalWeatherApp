package com.foo.umbrella.dagger;

import android.app.Application;

import com.foo.umbrella.data.ApiServicesProvider;
import com.foo.umbrella.data.api.WeatherService;
import com.foo.umbrella.dataFlow.Event;
import com.foo.umbrella.dataFlow.Reducer;
import com.foo.umbrella.dataFlow.State;
import com.foo.umbrella.dataFlow.Store;

import com.foo.umbrella.settings.SettingsDispatcher;
import com.foo.umbrella.weather.WeatherDispatcher;
import com.foo.umbrella.weather.models.CurrentConditionsVm;

import java.util.ArrayList;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

@Module
public class WeatherModule {

  public static final String STATE_OBSERVABLE = "StateObservable";

  private BehaviorSubject<State> stateSubject;
  private PublishSubject<Event> eventSubject;
  private Application application;

  public WeatherModule(Application application, BehaviorSubject<State> stateSubject) {
    State.Settings defaultSettings = new State.Settings(State.TemperatureUnit.Fahrenheit, -1);
    CurrentConditionsVm defaultConditions = new CurrentConditionsVm("-500", "Chilly", "CA?", 0);
    stateSubject.onNext(new State(defaultSettings, defaultConditions, new ArrayList<>(), false));
    this.stateSubject = stateSubject;
    this.eventSubject = PublishSubject.<Event>create();
    this.application = application;
  }

  @Provides
  @Named(STATE_OBSERVABLE)
  @Singleton
  Observable<State> providesStateObservable() {
    return stateSubject.hide();
  }

  @Provides
  @Singleton
  Store providesStore(Reducer reducer) {
    return new Store(reducer, stateSubject, eventSubject.hide());
  }

  @Provides
  @Singleton
  Reducer providesReducer() {
    return new Reducer();
  }

  @Provides
  @Singleton
  WeatherDispatcher providesWeatherDispatcher(WeatherService weatherService) {
    return new WeatherDispatcher(weatherService, eventSubject, -1);
  }

  @Provides
  @Singleton
  WeatherService providesWeatherService() {
    return new ApiServicesProvider(application).getWeatherService();
  }

  @Provides
  @Singleton
  SettingsDispatcher providesSettingsDispatcher() {
    return new SettingsDispatcher(eventSubject);
  }

}
