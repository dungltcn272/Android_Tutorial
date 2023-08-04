package com.example.retrofitandroidtutorial.api

import com.example.retrofitandroidtutorial.model.Currency
import com.example.retrofitandroidtutorial.model.Post
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
//https://jsonplaceholder.typicode.com/posts
interface ApiService {
    companion object {
        private val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        val apiService: ApiService = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }

    @GET("api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1")
    fun convertUsdToVnd(): Call<Currency>

    @GET("api/live")
    fun convertUsdToVnd2(@QueryMap options : Map<String, String>): Call<Currency>
    //http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1

    @GET("api/user/list")
    fun getListUser() : Call<Currency>

    @GET("api/group/{id}/users")
    fun getListUserFromGroup(@Path("id") groupId: Int): Call<Currency>

    @GET("api/group/{id}/users")
    fun getListCurrencyFromGroup2(@Path("id") groupId: Int, @Query("sort") sort: String):  Call<Currency>

    
    @POST("posts")
    fun sendPost(@Body post :Post) : Call<Post>
}
