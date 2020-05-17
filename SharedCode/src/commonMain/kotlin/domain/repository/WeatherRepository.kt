package com.owlsdevelopers.kmpweatherdemo.domain.repository

import domain.api.WeatherAPI
import domain.model.Weather

class WeatherRepository(val api: WeatherAPI) {
    suspend fun getWeather(cityId: String): Weather{
        return api.getWeather(cityId)
    }
}