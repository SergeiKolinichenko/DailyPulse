package my.mvi.dailypulse.sources.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceRaw(
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val desc: String,
    @SerialName("language")
    val language: String,
    @SerialName("country")
    val country: String
)
