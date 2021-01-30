package com.twitter.challenge.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.twitter.challenge.R
import com.twitter.challenge.databinding.FragmentFutureWeatherBinding
import com.twitter.challenge.model.WeatherListUI
import com.twitter.challenge.ui.adapter.MainAdapter
import com.twitter.challenge.ui.viewmodel.FutureWeatherViewModel
import com.twitter.challenge.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FutureWeatherFragment : Fragment(R.layout.fragment_future_weather) {

    private var binding: FragmentFutureWeatherBinding? = null
    private val futureWeatherViewModel : FutureWeatherViewModel by viewModels()
    private lateinit var adapter: MainAdapter

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
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter(arrayListOf())
        binding?.recyclerView?.addItemDecoration(
            DividerItemDecoration(binding?.recyclerView?.context, LinearLayoutManager.VERTICAL)
        )
        binding?.recyclerView?.adapter = adapter
    }

    private fun setupObserver() {
        futureWeatherViewModel.futureWeather.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.progressBar?.visibility = View.GONE
                    it.data?.let { users -> renderFiveDayList(users) }
                    binding?.recyclerView?.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                    binding?.recyclerView?.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderFiveDayList(fiveDayWeather: WeatherListUI) {
        binding?.standardDeviation?.text = fiveDayWeather.tempStandardDeviation?.toString().orEmpty()
        adapter.addData(fiveDayWeather)
        adapter.notifyDataSetChanged()
    }
}
