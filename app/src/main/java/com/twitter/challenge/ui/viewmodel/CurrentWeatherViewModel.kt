package com.twitter.challenge.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.twitter.challenge.model.WeatherUI
import com.twitter.challenge.repository.WeatherRepository
import com.twitter.challenge.utils.*
import kotlinx.coroutines.*
import timber.log.Timber

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

    fun retryFetchCurrentWeather() {
        fetchCurrentWeather()
    }

    private fun fetchCurrentWeather() {
        viewModelScope.launch {
            _currentWeather.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                weatherRepository.getCurrentWeather().let {
                    if (it.isSuccessful) {
                        _currentWeather.postValue(Resource.success(it.body()?.toWeatherUIModel(0)))
                    } else {
                        Timber.e(it.message())
                        _currentWeather.postValue(Resource.error(ErrorType.NETWORK_ERROR, null))
                    }
                }
            } else {
                _currentWeather.postValue(Resource.error(ErrorType.NO_INTERNET_DETECTED, null))
            }
        }
    }
}