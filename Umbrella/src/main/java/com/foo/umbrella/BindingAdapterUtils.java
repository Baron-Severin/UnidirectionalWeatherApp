package com.foo.umbrella;

import android.databinding.BindingAdapter;
import android.support.constraint.ConstraintLayout;

import com.foo.umbrella.dataFlow.State;

public class BindingAdapterUtils {

  @BindingAdapter("setHeaderColor")
  public static void setHeaderColor(ConstraintLayout layout, int color) {
    if (color == 0) {
      layout.setBackground(layout.getContext().getDrawable(R.color.weather_warm));
    } else if (color == 1) {
      layout.setBackground(layout.getContext().getDrawable(R.color.weather_cool));
    } else {
      throw new RuntimeException("Unexpected color used to set header background");
    }
  }

}
