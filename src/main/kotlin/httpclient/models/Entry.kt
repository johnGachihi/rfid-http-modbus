package httpclient.models

import httpclient.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class Entry(
    val id: Long,
    @Serializable(with = InstantSerializer::class)
    val entryTime: Instant,
    val ticketCode: String,
    @Serializable(with = InstantSerializer::class)
    val exitTime: Instant?,
)