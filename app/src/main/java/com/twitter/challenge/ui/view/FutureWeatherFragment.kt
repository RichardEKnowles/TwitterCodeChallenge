package com.twitter.challenge.ui.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.twitter.challenge.R
import com.twitter.challenge.databinding.FragmentFutureWeatherBinding
import com.twitter.challenge.model.WeatherListUI
import com.twitter.challenge.ui.adapter.WeatherAdapter
import com.twitter.challenge.ui.viewmodel.FutureWeatherViewModel
import com.twitter.challenge.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FutureWeatherFragment : Fragment(R.layout.fragment_future_weather) {

    private var binding: FragmentFutureWeatherBinding? = null
    private val futureWeatherViewModel : FutureWeatherViewModel by viewModels()
    private lateinit var adapter: WeatherAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentFutureWeatherBinding = FragmentFutureWeatherBinding.bind(view)
        binding = fragmentFutureWeatherBinding
        setupUI()
        setupObserver()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupUI() {
        // setup recyclerview
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = WeatherAdapter(arrayListOf())
        binding?.recyclerView?.addItemDecoration(
            DividerItemDecoration(binding?.recyclerView?.context, LinearLayoutManager.VERTICAL)
        )
        binding?.recyclerView?.adapter = adapter

        // setup click listeners
        binding?.includeErrorContainer?.retryButton?.setOnClickListener { futureWeatherViewModel.retryFetchFutureWeather() }
    }

    private fun setupObserver() {
        futureWeatherViewModel.futureWeather.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.progressBar?.isVisible = false
                    binding?.includeErrorContainer?.errorContainer?.isVisible = false
                    it.data?.let { fiveDayWeather -> renderFiveDayList(fiveDayWeather) }
                    binding?.recyclerView?.isVisible = true
                }
                Status.LOADING -> {
                    binding?.progressBar?.isVisible = true
                    binding?.includeErrorContainer?.errorContainer?.isVisible = false
                    binding?.recyclerView?.isVisible = false
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

    private fun renderFiveDayList(weatherListUI: WeatherListUI) {
        binding?.standardDeviation?.text = weatherListUI.tempStandardDeviation?.let {
            getString(R.string.standard_deviation, it.first, it.second)
        }.orEmpty()
        adapter.addData(weatherListUI)
        adapter.notifyDataSetChanged()
    }
}
