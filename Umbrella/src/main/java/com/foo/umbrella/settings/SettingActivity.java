package com.foo.umbrella.settings;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.foo.umbrella.R;
import com.foo.umbrella.UmbrellaApp;
import com.foo.umbrella.dataFlow.State;
import com.foo.umbrella.weather.WeatherActivity;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static com.foo.umbrella.dagger.WeatherModule.STATE_OBSERVABLE;

public class SettingActivity extends AppCompatActivity {

  @Inject SettingsDispatcher settingsDispatcher;
  @Inject @Named(STATE_OBSERVABLE) Observable<State> stateObservable;
  private Disposable disposable;

  private Toolbar toolbar;
  private LinearLayout unitLayout;
  private LinearLayout zipLayout;
  private TextView currentZip;
  private TextView currentUnit;

  private boolean zipRequested = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_setting);
    ((UmbrellaApp)getApplication()).getAppComponent().inject(this);

    toolbar = findViewById(R.id.toolbar);
    unitLayout = findViewById(R.id.ll_unit);
    zipLayout = findViewById(R.id.ll_zip);
    currentUnit = findViewById(R.id.tv_currentUnits);
    currentZip = findViewById(R.id.tv_currentZip);

    toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.content_background));
    toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.settings_toolbar));
    toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_24dp));
    setSupportActionBar(toolbar);
    toolbar.setNavigationOnClickListener((view) -> onBackPressed());
    unitLayout.setOnClickListener((view) -> onUnitClicked());
    zipLayout.setOnClickListener((view) -> onZipClicked());

    disposable = stateObservable.subscribe((state) -> {
      currentUnit.setText(state.getSettings().getUnit().toString());
      String zipText = String.valueOf(state.getSettings().getZip());
      if (zipText.equals("-1")) zipText = "Unknown";
      currentZip.setText(zipText);
    });
    if (getIntent().getExtras() != null) {
      zipRequested = getIntent().getExtras().getBoolean("REQUEST_ZIP");
    }
    if (zipRequested) {
      onZipClicked();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    disposable.dispose();
  }

  private void onUnitClicked() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Select your preferred unit of measurement");
    builder.setPositiveButton("Fahrenheit", (dialogInterface, i) -> settingsDispatcher.onUnitClicked(State.TemperatureUnit.Fahrenheit));
    builder.setNegativeButton("Celcius", ((dialogInterface, i) -> settingsDispatcher.onUnitClicked(State.TemperatureUnit.Celcius)));
    builder.create().show();
  }

  private void onZipClicked() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Select your Zip code");
    EditText input = new EditText(SettingActivity.this);
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT);
    input.setInputType(InputType.TYPE_CLASS_NUMBER);
    builder.setView(input);
    builder.setPositiveButton("Continue", ((dialogInterface, i) -> {
      settingsDispatcher.onZipClicked(input.getText().toString());
      if (zipRequested) {
        this.finish();
        zipRequested = false;
      }
    }));
    builder.setNegativeButton("Cancel", ((dialogInterface, i) -> dialogInterface.cancel()));
    builder.create().show();

  }

}
