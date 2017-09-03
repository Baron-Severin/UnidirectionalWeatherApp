package com.foo.umbrella.dagger;

import com.foo.umbrella.settings.SettingActivity;
import com.foo.umbrella.weather.WeatherActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {WeatherModule.class})
public interface AppComponent {
  void inject(WeatherActivity weatherActivity);
  void inject(SettingActivity settingsActivity);
}
