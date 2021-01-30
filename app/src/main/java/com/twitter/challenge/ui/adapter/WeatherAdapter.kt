package com.twitter.challenge.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.twitter.challenge.R
import com.twitter.challenge.databinding.ItemWeatherBinding
import com.twitter.challenge.model.WeatherListUI
import com.twitter.challenge.model.WeatherUI
import com.twitter.challenge.utils.safeLet
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WeatherAdapter(
    private val weatherList: ArrayList<WeatherUI>
) : RecyclerView.Adapter<WeatherAdapter.DataViewHolder>() {

    class DataViewHolder(private val itemBinding: ItemWeatherBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(weather: WeatherUI) {
            itemBinding.apply {
                val context = itemView.context
                date.text = weather.date?.let { SimpleDateFormat("EEEE, MMMM dd", Locale.getDefault()).format(it) }.orEmpty()
                safeLet(weather.tempCelsius, weather.tempFahrenheit) { celsius, fahrenheit ->
                    temperature.text = context.getString(R.string.temperature, celsius, fahrenheit)
                }
                windSpeed.text = weather.windSpeed?.let { context.getString(R.string.wind_speed, it) }.orEmpty()
                cloudIcon.isVisible = weather.showCloudIcon
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding: ItemWeatherBinding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    fun addData(weatherListUI: WeatherListUI) {
        weatherListUI.weatherList?.let { weatherList.addAll(it) }
    }
}