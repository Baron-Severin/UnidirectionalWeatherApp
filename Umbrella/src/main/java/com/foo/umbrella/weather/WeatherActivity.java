package com.foo.umbrella.weather;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.foo.umbrella.R;
import com.foo.umbrella.dataFlow.State;
import com.foo.umbrella.dataFlow.Store;
import com.foo.umbrella.UmbrellaApp;
import com.foo.umbrella.databinding.ActivityWeatherBinding;
import com.foo.umbrella.weather.adapters.ForecastCardAdapter;
import com.foo.umbrella.weather.models.CurrentConditionsVm;
import com.foo.umbrella.weather.models.ForecastCardModel;
import com.foo.umbrella.weather.models.ForecastHourModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

import static com.foo.umbrella.dagger.WeatherModule.STATE_OBSERVABLE;

public class WeatherActivity extends AppCompatActivity {

  @Inject Store store;
  @Inject WeatherDispatcher weatherDispatcher;
  @Inject @Named(STATE_OBSERVABLE) Observable<State> stateObservable;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ((UmbrellaApp)getApplication()).getAppComponent().inject(this);

    ActivityWeatherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
    stateObservable.subscribe((state) -> {
      binding.rvForecast.setAdapter(new ForecastCardAdapter(state.getCards()));
      binding.setCurrentVm(state.getCurrentConditionsVm());
    });
    weatherDispatcher.setZip(99501);

    binding.rvForecast.setLayoutManager(new LinearLayoutManager(this));
  }

  @Override
  protected void onResume() {
    super.onResume();
    weatherDispatcher.requestUpdate();
  }
}
