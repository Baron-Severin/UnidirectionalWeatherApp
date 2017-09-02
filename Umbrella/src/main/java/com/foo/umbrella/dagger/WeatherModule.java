package com.foo.umbrella.dagger;

import com.foo.umbrella.Reducer;
import com.foo.umbrella.Store;
import com.foo.umbrella.weather.WeatherDispatcher;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherModule {

  @Provides
  @Singleton
  Store providesStore() {
    return new Store();
  }

  @Provides
  @Singleton
  Reducer providesReducer() {
    return new Reducer();
  }

  @Provides
  @Singleton
  WeatherDispatcher providesWeatherDispatcher() {
    return new WeatherDispatcher();
  }


}
