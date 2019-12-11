package com.adrian.bucayan.ui.viewmodels.authentication

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adrian.bucayan.data.remote.ApiInterface
import com.adrian.bucayan.di.Repository
import com.adrian.bucayan.models.ItunesResponse
import com.adrian.bucayan.models.Results
import com.google.gson.JsonParser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject



class MyViewModel @Inject constructor(private val apiInterface: ApiInterface,
                                      private val repository: Repository) : ViewModel() {

    lateinit var iTunesResponseObserver: DisposableObserver<Response<ItunesResponse>>
    var iTunesResponseResult: MutableLiveData<Response<ItunesResponse>> = MutableLiveData()
    var iTunesResponseError: MutableLiveData<String> = MutableLiveData()
    var iTunesResponseLoader: MutableLiveData<Boolean> = MutableLiveData()

    fun iTunesResponseResult(): LiveData<Response<ItunesResponse>> {
        return iTunesResponseResult
    }

    fun iTunesResponseError(): LiveData<String> {
        return iTunesResponseError
    }

    fun iTunesResponseLoader(): LiveData<Boolean> {
        return iTunesResponseLoader
    }

    fun search(search: String, iso: String, media: String) {

        iTunesResponseObserver  = object : DisposableObserver<Response<ItunesResponse>>() {

            @SuppressLint("TimberArgCount")
            override fun onNext(t: Response<ItunesResponse>) {
                when {
                    t.isSuccessful -> iTunesResponseResult.postValue(t)
                    //t.code() == 403 -> iTunesResponseError.postValue("Old Password does not match")
                    else -> {
                        var message = "An error occurred"
                        val errorJsonString = t.errorBody()?.string()
                        message = JsonParser().parse(errorJsonString)
                                .asJsonObject["message"]
                                .asString
                        Timber.e("message ", message)
                    }
                }
                iTunesResponseLoader.postValue(false)
            }

            override fun onComplete() {}

            override fun onError(e: Throwable) {
                iTunesResponseLoader.postValue(false)

                assert(e != null)
                if (e is HttpException) {
                    Timber.e("Error code = %s", e.code().toString())
                    if (e.code() == HttpURLConnection.HTTP_BAD_REQUEST) {
                        Timber.e("onError SocketTimeout* " + "HTTP_BAD_REQUEST")
                        iTunesResponseError.postValue("")
                    } else {
                        val responseBody = (e as HttpException).response().errorBody()
                        iTunesResponseError.postValue(responseBody!!.string())
                        Timber.e("onError SocketTimeout* " + "Network TimeOut")
                    }
                }
            }


        }

        repository.queryItunesApi(search,iso,media)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(100, MILLISECONDS)
                .subscribe(iTunesResponseObserver)

    }

}