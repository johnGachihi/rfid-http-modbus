package httpclient

import kotlinx.coroutines.future.await
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import httpclient.models.Entry
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class HttpClient(
    private val address: String = "http://localhost",
    private val port: Int = 8080
) {
    private val <T>HttpResponse<T>.isSuccess: Boolean
        get() = this.statusCode() in 200..299

    suspend fun addEntry(rfid: Int): Response<Entry> {
        val request = HttpRequest.newBuilder()
            .uri(URI("$address:$port/entry"))
            .header("Accept", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(rfid.toString()))
            .build()

        return HttpClient.newBuilder()
            .build()
            .sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply<Response<Entry>?>(::toJson)
            .await()
    }

    private inline fun <reified T>toJson(res: HttpResponse<String>): Response<T> {
        val json = Json { ignoreUnknownKeys = true }
        return when (res.isSuccess) {
            true -> Response.Success(json.decodeFromString(res.body()))
            else -> Response.Failure(json.decodeFromString(res.body()))
        }
    }
}
