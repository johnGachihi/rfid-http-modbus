package httpclient

import models.Entry
import retrofit2.Call
import retrofit2.Callback

class EntryRepository(
    private val service: EntryService =
        RetrofitFactory.create(EntryService::class.java)
) {
    fun addEntryAsync(rfid: String, callback: (Response<Entry>?) -> Unit) {
        service.addEntry(rfid).enqueue(object: Callback<Entry> {
            override fun onResponse(
                call: Call<Entry>,
                response: retrofit2.Response<Entry>
            ) {
                if (response.isSuccessful) {
                    callback(Response.Success(response.body()!!))
                } else {
//                    callback(Response.Failure())
                    println("Not successful: ${response.code()}")
                    println(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<Entry>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}