package models

import java.time.LocalDateTime
import java.util.*

data class Entry(
    val id: Long,
    val entryTime: Date,
    val ticketCode: String,
    val exitTime: LocalDateTime?,
)