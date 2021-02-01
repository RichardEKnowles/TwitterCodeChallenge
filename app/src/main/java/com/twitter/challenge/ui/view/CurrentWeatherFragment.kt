package com.twitter.challenge.ui.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.twitter.challenge.R
import com.twitter.challenge.databinding.FragmentCurrentWeatherBinding
import com.twitter.challenge.model.WeatherUI
import com.twitter.challenge.ui.viewmodel.CurrentWeatherViewModel
import com.twitter.challenge.utils.Status
import com.twitter.challenge.utils.safeLet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment(R.layout.fragment_current_weather) {

    private var binding: FragmentCurrentWeatherBinding? = null
    private val currentWeatherViewModel : CurrentWeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentCurrentWeatherBinding = FragmentCurrentWeatherBinding.bind(view)
        binding = fragmentCurrentWeatherBinding
        setupUI()
        setupObserver()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupUI() {
        binding?.includeErrorContainer?.retryButton?.setOnClickListener { currentWeatherViewModel.retryFetchCurrentWeather() }
    }

    private fun setupObserver() {
        currentWeatherViewModel.currentWeather.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.progressBar?.isVisible = false
                    binding?.includeErrorContainer?.errorContainer?.isVisible = false
                    it.data?.let { weather -> renderList(weather) }
                    binding?.weatherContainer?.isVisible = true
                }
                Status.LOADING -> {
                    binding?.progressBar?.isVisible = true
                    binding?.includeErrorContainer?.errorContainer?.isVisible = false
                    binding?.weatherContainer?.isVisible = true
                }
                Status.ERROR -> {
                    binding?.progressBar?.isVisible = false
                    binding?.includeErrorContainer?.errorContainer?.isVisible = true
                    val errorMessage = it.messageRes?.let { errorMessageRes -> getString(errorMessageRes) }
                    binding?.includeErrorContainer?.errorMessage?.text = errorMessage.orEmpty()
                }
            }
        })
    }

    private fun renderList(weather: WeatherUI) {
        binding?.apply {
            name.text = weather.name.orEmpty()
            safeLet(weather.tempCelsius, weather.tempFahrenheit) { celsius, fahrenheit ->
                temperature.text = getString(R.string.temperature, celsius, fahrenheit)
            }
            windSpeed.text = weather.windSpeed?.let { getString(R.string.wind_speed, it) }.orEmpty()
            cloudIcon.isVisible = weather.showCloudIcon
        }
    }
}