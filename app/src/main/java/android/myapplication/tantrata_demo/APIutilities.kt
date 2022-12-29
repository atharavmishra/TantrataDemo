package android.myapplication.tantrata_demo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHelper {

    val baseUrl = "https://api.imgur.com/3/gallery/"
    val baseUrl2 = "https://api.imgur.com/3/gallery/hot/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
    fun getInstance2(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl2)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}