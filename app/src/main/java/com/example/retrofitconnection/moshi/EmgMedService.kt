package com.example.retrofitconnection.moshi

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EmgMedService {
    @GET("CORSVST")
    fun getEmgMedData(@Query("Key") Key : String,
                      @Query("Type") Type : String):Call<CovidCenterResponse>

    @GET("CORSVST")
    fun getDataCoroutine(
        @Query("KEY") KEY : String,
        @Query("Type") Type : String
    ): Response<CovidCenterResponse>

    //BASE_URL/CORSVST?KEY="KEY"&Type="Type"

    //
}