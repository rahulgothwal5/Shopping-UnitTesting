package com.example.shopinglisttestin.data_lib.data_source.remote.service

import com.androiddevs.shoppinglisttestingyt.data.remote.responses.ImageResponse
import com.example.shopinglisttestin.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaBayService {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<ImageResponse>
}