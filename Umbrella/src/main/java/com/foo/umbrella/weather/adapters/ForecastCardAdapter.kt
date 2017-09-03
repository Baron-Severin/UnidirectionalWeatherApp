package com.foo.umbrella.weather.adapters

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.foo.umbrella.R
import com.foo.umbrella.weather.models.ForecastCardModel
import kotlinx.android.synthetic.main.item_forecast_card.view.*


class ForecastCardAdapter(val models: List<ForecastCardModel>) : RecyclerView.Adapter<ForecastCardAdapter.ViewHolder>() {

  inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val rvHours : RecyclerView
    val context : Context
    init {
      rvHours = view.rv_hours
      context = view.context
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    val inflator = LayoutInflater.from(parent?.context)
    val view = inflator.inflate(R.layout.item_forecast_card, parent, false);
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val model = models[position]
    holder.rvHours.adapter = ForecastHourAdapter(model.hourModels)
    holder.rvHours.layoutManager = GridLayoutManager(holder.context, 4)
  }

  override fun getItemCount(): Int {
    return models.size
  }
}