package com.foo.umbrella;

import android.app.Application;

import com.foo.umbrella.dagger.AppComponent;
import com.foo.umbrella.dagger.DaggerAppComponent;
import com.jakewharton.threetenabp.AndroidThreeTen;

public class UmbrellaApp extends Application {

  private AppComponent appComponent;

  @Override public void onCreate() {
    super.onCreate();
    AndroidThreeTen.init(this);

    appComponent = DaggerAppComponent.builder()
        .build();
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }
}
