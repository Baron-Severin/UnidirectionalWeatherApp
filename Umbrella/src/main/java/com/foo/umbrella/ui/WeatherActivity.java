package com.foo.umbrella.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.foo.umbrella.R;
import com.foo.umbrella.data.ApiServicesProvider;
import com.foo.umbrella.databinding.ActivityWeatherBinding;
import com.foo.umbrella.weather.models.CurrentConditionsVm;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_weather);


    CurrentConditionsVm currentVm = new CurrentConditionsVm("100*", "Goodish", "Walnut Creek");
    ActivityWeatherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
    binding.setCurrentVm(currentVm);

  }
}
