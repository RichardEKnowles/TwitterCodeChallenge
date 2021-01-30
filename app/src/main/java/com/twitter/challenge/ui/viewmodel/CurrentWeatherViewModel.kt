package com.twitter.challenge.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.twitter.challenge.model.WeatherUI
import com.twitter.challenge.repository.WeatherRepository
import com.twitter.challenge.utils.NetworkHelper
import com.twitter.challenge.utils.Resource
import com.twitter.challenge.utils.toWeatherUIModel
import kotlinx.coroutines.*

class CurrentWeatherViewModel @ViewModelInject constructor(
        private val weatherRepository: WeatherRepository,
        private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _currentWeather = MutableLiveData<Resource<WeatherUI>>()
    val currentWeather: LiveData<Resource<WeatherUI>>
        get() = _currentWeather

    init {
        fetchCurrentWeather()
    }

    private fun fetchCurrentWeather() {
        viewModelScope.launch {
            _currentWeather.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                weatherRepository.getCurrentWeather().let {
                    if (it.isSuccessful) {
                        _currentWeather.postValue(Resource.success(it.body()?.toWeatherUIModel(0)))
                    } else _currentWeather.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else {
                // TODO: Create a resource provider and pull strings from strings.xml
                _currentWeather.postValue(Resource.error("No internet connection", null))
            }
        }
    }
}