package domain.model
import kotlinx.serialization.Serializable

@Serializable
data class Coord(val lon: Float = 0f,
                 val lat: Float = 0f)