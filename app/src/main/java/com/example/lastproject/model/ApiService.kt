package com.example.lastproject.model

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/most-popular")
    fun GetMostPopular(@Query("page")page :Int = 1) : Call<ApiResponse>

    @GET("/api/show-details")
    fun GetTvShow(@Query("q")q : String) : Call<ApiDetailResponse>


    @GET("/api/search")
    fun SearchTvShow(@Query("q")q : String, @Query("page")page :Int = 1) : Call<ApiResponse>

    companion object{

        private var _instance : ApiService? = null


        fun getInstance() : ApiService{

            if(_instance == null){
                val retrofit =  Retrofit.Builder()
                    .baseUrl("https://www.episodate.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().build())
                    .build();
                _instance = retrofit.create(
                    ApiService::class.java)
            }
            return _instance!!

        }


    }
}