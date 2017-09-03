package com.foo.umbrella;

import android.app.Application;

import com.foo.umbrella.dagger.AppComponent;
import com.foo.umbrella.dagger.DaggerAppComponent;
import com.foo.umbrella.dagger.WeatherModule;
import com.foo.umbrella.dataFlow.State;
import com.foo.umbrella.dataFlow.Store;
import com.jakewharton.threetenabp.AndroidThreeTen;

import io.reactivex.subjects.BehaviorSubject;

public class UmbrellaApp extends Application {

  private AppComponent appComponent;

  @Override public void onCreate() {
    super.onCreate();
    AndroidThreeTen.init(this);
    appComponent = DaggerAppComponent.builder()
        .weatherModule(new WeatherModule(this, BehaviorSubject.<State>create()))
        .build();
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }
}
