package httpclient.models

import httpclient.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class ErrorResponse(
    @Serializable(with = InstantSerializer::class)
    val timestamp: Instant,
    val message: String,
    val status: Int,
    val error: String,
)
