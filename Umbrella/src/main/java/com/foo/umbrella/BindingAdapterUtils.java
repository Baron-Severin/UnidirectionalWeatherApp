package com.foo.umbrella;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;

import com.foo.umbrella.dataFlow.State;

public class BindingAdapterUtils {

  @BindingAdapter("setHeaderColor")
  public static void setHeaderColor(ConstraintLayout layout, int color) {
    Toolbar toolbar = layout.getRootView().findViewById(R.id.toolbar);
    Drawable warm = layout.getContext().getDrawable(R.color.weather_warm);
    Drawable cool = layout.getContext().getDrawable(R.color.weather_cool);

    if (color == 0) {
      layout.setBackground(warm);
      toolbar.setBackground(warm);
    } else if (color == 1) {
      layout.setBackground(cool);
      toolbar.setBackground(cool);
    } else {
      throw new RuntimeException("Unexpected color used to set header background");
    }
  }

}
