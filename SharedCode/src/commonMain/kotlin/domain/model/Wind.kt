package domain.model
import kotlinx.serialization.Serializable

@Serializable
data class Wind(val deg: Double = 0.0,
                val speed: Double = 0.0)