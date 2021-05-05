import httpclient.HttpClient
import httpclient.Response
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import httpclient.models.Entry
import httpclient.models.ErrorResponse
import rfidclient.RfidClient
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*

fun main() {
    RfidClient.listenForRfid("192.168.1.200", 4196) { rfid ->
        println("Rfid: $rfid")
        when (val response = HttpClient().addEntry(rfid)) {
            is Response.Success -> println(response.data)
            is Response.Failure -> println(response.data)
        }
    }
}