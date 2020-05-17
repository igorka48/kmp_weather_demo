package domain.model
import kotlinx.serialization.Serializable

@Serializable
data class WeatherItem(val icon: String = "",
                       val description: String = "",
                       val main: String = "",
                       val id: Int = 0)