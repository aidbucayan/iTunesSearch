package com.adrian.bucayan.di

import com.adrian.bucayan.data.remote.ApiInterface
import com.adrian.bucayan.models.ItunesResponse
import com.adrian.bucayan.utils.Pref
import com.adrian.bucayan.utils.Utils
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(val apiInterface: ApiInterface, val utils: Utils, val pref: Pref) {

    fun queryItunesApi(term: String?, country: String?, media: String?) :  Observable<Response<ItunesResponse>> {
        return  apiInterface.getQuery(term, country, media)
                .doOnNext {}
    }


}