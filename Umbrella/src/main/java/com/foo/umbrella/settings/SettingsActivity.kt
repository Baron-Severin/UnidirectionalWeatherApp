package com.foo.umbrella.settings

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.View
import com.foo.umbrella.R
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.toolbar.*

class SettingsActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)

    val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
    toolbar.setTitleTextColor(ContextCompat.getColor(baseContext, R.color.content_background))
    toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.settings_toolbar))
    toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_24dp));
    setSupportActionBar(toolbar)
    toolbar.setNavigationOnClickListener { onBackPressed() }
    ll_unit.setOnClickListener { onUnitClicked() }
  }

  fun onUnitClicked() {
    
  }

}
