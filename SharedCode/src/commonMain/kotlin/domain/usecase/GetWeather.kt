package com.owlsdevelopers.kmpweatherdemo.domain.usecase

import com.owlsdevelopers.kmpweatherdemo.coroutineScope
import com.owlsdevelopers.kmpweatherdemo.domain.repository.WeatherRepository
import domain.model.Weather
import kotlinx.coroutines.launch

class GetWeather(val weatherRepository: WeatherRepository) {

    val cityId = "3095013"//"709930" //3095013

    fun execute(completion: (Weather) -> Unit, failure: () -> Unit) {
        coroutineScope.launch {
            var weather: Weather? = null
            try {
                weather = weatherRepository.getWeather(cityId)
            } catch (e: Exception) {
                println(e.message)
            }
            if (weather != null) {
                completion(weather)
            } else {
                failure()
            }
        }
    }
}