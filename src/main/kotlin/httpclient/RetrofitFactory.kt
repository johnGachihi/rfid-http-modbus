package httpclient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<T> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}