package com.foo.umbrella.weather.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.foo.umbrella.R
import com.foo.umbrella.weather.models.ForecastHourModel
import kotlinx.android.synthetic.main.item_forecast_hour.view.*

class ForecastHourAdapter(val models: List<ForecastHourModel>) : RecyclerView.Adapter<ForecastHourAdapter.ViewHolder>() {

  inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvHour : TextView
    val tvTemperature: TextView
    val ivIcon : ImageView
    init {
      tvHour = view.tv_hour
      tvTemperature = view.tv_temperature
      ivIcon = view.iv_icon
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    val inflator = LayoutInflater.from(parent?.context)
    val view = inflator.inflate(R.layout.item_forecast_hour, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val model = models[position]
    holder.tvHour.text = model.hour
    holder.tvTemperature.text = model.temperature
    val id = holder.ivIcon.context.getResources().getIdentifier(model.icon, "drawable", holder.ivIcon.context.getPackageName())
    holder.ivIcon.setImageResource(id)
  }

  override fun getItemCount(): Int {
    return models.size
  }
}