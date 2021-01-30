package com.twitter.challenge.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.twitter.challenge.R
import com.twitter.challenge.databinding.FragmentCurrentWeatherBinding
import com.twitter.challenge.model.WeatherUI
import com.twitter.challenge.ui.viewmodel.CurrentWeatherViewModel
import com.twitter.challenge.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment(R.layout.fragment_current_weather) {

    private var binding: FragmentCurrentWeatherBinding? = null
    private val currentWeatherViewModel : CurrentWeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentCurrentWeatherBinding = FragmentCurrentWeatherBinding.bind(view)
        binding = fragmentCurrentWeatherBinding
        setupObserver()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupObserver() {
        currentWeatherViewModel.currentWeather.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.progressBar?.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding?.weatherContainer?.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                    binding?.weatherContainer?.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(weather: WeatherUI) {
        binding?.name?.text = weather.name
        binding?.conditions?.text = weather.toString()
    }
}