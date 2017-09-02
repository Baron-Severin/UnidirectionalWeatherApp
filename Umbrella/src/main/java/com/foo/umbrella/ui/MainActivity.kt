package com.foo.umbrella.ui

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.foo.umbrella.R
import com.foo.umbrella.databinding.ActivityMainBinding
import com.foo.umbrella.weather.models.CurrentConditionsVm

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)

    val currentVm = CurrentConditionsVm("100*", "Goodish", "Walnut Creek")
//    val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

//    binding.currentVm = currentVm
  }
}
