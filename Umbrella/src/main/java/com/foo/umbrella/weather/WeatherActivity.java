package com.foo.umbrella.weather;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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

  private Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ((UmbrellaApp)getApplication()).getAppComponent().inject(this);

    ActivityWeatherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
    binding.rvForecast.setLayoutManager(new LinearLayoutManager(this));

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(), R.color.content_background));
    setSupportActionBar(toolbar);

    weatherDispatcher.setZip(99501);
//    weatherDispatcher.setZip(94597);
    stateObservable.subscribe((state) -> {
      binding.rvForecast.setAdapter(new ForecastCardAdapter(state.getCards()));
      binding.setCurrentVm(state.getCurrentConditionsVm());
      toolbar.setTitle(state.getCurrentConditionsVm().getLocation());
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    weatherDispatcher.requestUpdate();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.settings, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case R.id.item_settings:
        // todo start settings activity here
        break;
      default:
        break;
    }
    return true;
  }
}
