package com.adrian.bucayan.data.remote

import com.adrian.bucayan.models.ItunesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Adrian Bucayan
 */

interface ApiInterface {

    //https://itunes.apple.com/search?term=star&amp;country=au&amp;media=movie&amp;all
   @GET("search")
    fun getQuery(@Query("term") term: String?,
                 @Query("country") country: String?,
                 @Query("media") media: String?): Call<Response<ItunesResponse>>
}