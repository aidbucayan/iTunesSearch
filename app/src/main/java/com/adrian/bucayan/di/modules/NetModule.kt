package com.adrian.bucayan.di.modules

/**
 * @author Adrian Bucayan
 */

import android.annotation.SuppressLint
import android.app.Application
import android.content.SharedPreferences
import com.adrian.bucayan.BuildConfig
import com.adrian.bucayan.data.remote.ApiInterface
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Adrian Bucayan
 */

@Module
class NetModule(private val baseUrl: String, val app: Application) {

    private val cacheSize = 10 * 1024 * 1024
    private var cache: okhttp3.Cache? = null
    private var retry: Boolean = false
    private val logging = HttpLoggingInterceptor()

    val PREFS_FILENAME = "pref"
    val prefs: SharedPreferences = app.getSharedPreferences(PREFS_FILENAME, 0)

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @SuppressLint("TimberArgCount", "NewApi")
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        // Okhttp cache
        cache = okhttp3.Cache(app.cacheDir, cacheSize.toLong())
        // Okhttp builder
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES)
        builder.cache(cache)
        // Enable retry for Get methods
        builder.retryOnConnectionFailure(retry)

        // Enable log on Debug mode
        if (BuildConfig.DEBUG) {
            builder.interceptors().add(logging)
            retrofitLogger()
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun getObjectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()

        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(JodaModule())

        return objectMapper
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(JacksonConverterFactory.create(getObjectMapper()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiInterface(retrofit: Retrofit): ApiInterface = retrofit.create(
        ApiInterface::class.java)

    fun retrofitLogger() {
        // Setting the Retrofit logs
        logging.level = HttpLoggingInterceptor.Level.BODY
    }

}