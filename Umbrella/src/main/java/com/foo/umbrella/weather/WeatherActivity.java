package com.foo.umbrella.weather;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.foo.umbrella.R;
import com.foo.umbrella.Store;
import com.foo.umbrella.UmbrellaApp;
import com.foo.umbrella.databinding.ActivityWeatherBinding;
import com.foo.umbrella.weather.adapters.ForecastCardAdapter;
import com.foo.umbrella.weather.models.CurrentConditionsVm;
import com.foo.umbrella.weather.models.ForecastCardModel;
import com.foo.umbrella.weather.models.ForecastHourModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class WeatherActivity extends AppCompatActivity {

  @Inject Store store;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ((UmbrellaApp)getApplication()).getAppComponent().inject(this);

    CurrentConditionsVm currentVm = new CurrentConditionsVm("100*", "Goodish", "Walnut Creek");
    ActivityWeatherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
    binding.setCurrentVm(currentVm);


    // todo temp hardcoding
    List<ForecastCardModel> cards = new ArrayList<>();
    List<ForecastHourModel> hours = new ArrayList<>();
    hours.add(new ForecastHourModel("100*", R.drawable.weather_cloudy, "1:00PM"));
    hours.add(new ForecastHourModel("100*", R.drawable.weather_cloudy, "2:00PM"));
    hours.add(new ForecastHourModel("100*", R.drawable.weather_cloudy, "3:00PM"));
    hours.add(new ForecastHourModel("100*", R.drawable.weather_cloudy, "4:00PM"));
    hours.add(new ForecastHourModel("100*", R.drawable.weather_cloudy, "5:00PM"));
    cards.add(new ForecastCardModel(0, "Today", hours));

    ForecastCardAdapter cardAdapter = new ForecastCardAdapter(cards);
    binding.rvForecast.setAdapter(cardAdapter);
    binding.rvForecast.setLayoutManager(new LinearLayoutManager(this));
    // todo end temp hardcoding
  }
}
