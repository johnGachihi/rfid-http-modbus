package models

import java.util.*

data class ErrorResponse(
    val timestamp: Date,
    val message: String,
    val status: Int,
)
