package httpclient

import models.Entry
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EntryService {
    @POST("entry")
    fun addEntry(@Body rfid: String): Call<Entry>
}