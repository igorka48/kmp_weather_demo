package domain.api

import domain.model.Weather
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import kotlinx.serialization.json.Json

class WeatherAPI(
    private val client: HttpClient = HttpClient()
) {

    private suspend fun request(urlString: String): String {
        return client.get<HttpResponse>(urlString).readText()
    }

    private suspend fun requestWeatherByCityId(cityId: String): Weather {
        val result = request(WEATHER_URL)
        return Json.nonstrict.parse(Weather.serializer(), result)
    }

    suspend fun getWeather(cityId: String): Weather = requestWeatherByCityId(cityId)

    companion object {
        const val WEATHER_URL =
            "https://api.openweathermap.org/data/2.5/weather?id=709930&appid=ec8158e15fdb24b438ed4d737233fc27"
    }
}