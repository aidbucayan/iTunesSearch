package com.adrian.bucayan.di

import android.util.Log
import com.adrian.bucayan.data.remote.ApiInterface
import com.adrian.bucayan.models.ItunesResponse
import com.adrian.bucayan.utils.Pref
import com.adrian.bucayan.utils.Utils
import io.reactivex.Observable
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject


class Repository @Inject constructor(val apiInterface: ApiInterface, val utils: Utils, val pref: Pref) {

    fun queryItunesApi(term: String?, country: String?, media: String?) : Response<ItunesResponse> {
        return  apiInterface.getQuery(term, country, media)
                .doOnNext {}
    }


}