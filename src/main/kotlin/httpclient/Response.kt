package httpclient

import httpclient.models.ErrorResponse

sealed class Response<out T> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Failure(val data: ErrorResponse) : Response<Nothing>()
}
