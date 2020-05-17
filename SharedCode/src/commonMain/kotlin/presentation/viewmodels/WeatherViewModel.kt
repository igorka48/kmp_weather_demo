package com.owlsdevelopers.kmpweatherdemo.presentation.viewmodels

import com.owlsdevelopers.kmpweatherdemo.di.KodeinInjector
import com.owlsdevelopers.kmpweatherdemo.domain.usecase.GetWeather
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.model.Weather
import org.kodein.di.erased.instance

class WeatherViewModel : ViewModel() {
    private val getWeather by KodeinInjector.instance<GetWeather>()
    private val mWeatherLiveData = MutableLiveData<Weather?>(null)
    val weatherLiveData
        get() = mWeatherLiveData

    init {
        getWeather.execute({
            mWeatherLiveData.postValue(it)
        }, {

        })
    }
}
