package com.twitter.challenge.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.twitter.challenge.model.WeatherListUI
import com.twitter.challenge.model.WeatherUI
import com.twitter.challenge.repository.WeatherRepository
import com.twitter.challenge.utils.*
import kotlinx.coroutines.*
import timber.log.Timber

class FutureWeatherViewModel @ViewModelInject constructor(
        private val weatherRepository: WeatherRepository,
        private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _futureWeather = MutableLiveData<Resource<WeatherListUI>>()
    val futureWeather: LiveData<Resource<WeatherListUI>>
        get() = _futureWeather

    init {
        fetchFutureDaysOfWeather()
    }

    fun retryFetchFutureWeather() {
        fetchFutureDaysOfWeather()
    }

    private fun fetchFutureDaysOfWeather() {
        viewModelScope.launch {
            _futureWeather.postValue(Resource.loading(null))
            try {
                if (networkHelper.isNetworkConnected()) {
                    coroutineScope {
                       val listDeferred = mutableListOf<Deferred<WeatherUI?>>()
                        for (i in 1 until FETCH_NEXT_N_DAYS + 1) {
                            listDeferred.add(async { weatherRepository.getFutureWeather(i).let {
                                if (it.isSuccessful) it.body()?.toWeatherUIModel(i) else {
                                    Timber.e(it.message())
                                    cancel()
                                    _futureWeather.postValue(Resource.error(ErrorType.NETWORK_ERROR, null))
                                    null
                                } }
                            })
                        }

                        val list = listDeferred.awaitAll().filterNotNull()
                        val weatherListUI = WeatherListUI(list.calcTempStandardDeviation(), list)
                        _futureWeather.postValue(Resource.success(weatherListUI))
                    }
                } else {
                    _futureWeather.postValue(Resource.error(ErrorType.NO_INTERNET_DETECTED, null))
                }
            } catch (e: Exception) {
                _futureWeather.postValue(Resource.error(ErrorType.GENERIC, null))
            }
        }
    }
}