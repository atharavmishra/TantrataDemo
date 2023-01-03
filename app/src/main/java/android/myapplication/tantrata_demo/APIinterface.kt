package android.myapplication.tantrata_demo

import android.myapplication.tantrata_demo.ModelClass.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIinterface {
    @GET("viral/0.json")
    fun getviral(): Call<Data>


    @Headers("authorization: Client-ID cf8402138efce6e")
    @GET("search")
    fun getData(
        @Query("q") query: String
    ): Call<Data>

}