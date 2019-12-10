package com.adrian.bucayan

import android.app.Activity
import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.adrian.bucayan.di.component.DaggerAppComponent
import com.adrian.bucayan.di.modules.AppModule
import com.adrian.bucayan.di.modules.NetModule
import com.adrian.bucayan.utils.Constants
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

/**
 * @author Adrian Bucayan
 */

class Application : MultiDexApplication(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule(Constants.SERVER, this))
                .build().inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

    }

    override fun attachBaseContext(base: Context?) {
        MultiDex.install(this)
        super.attachBaseContext(base)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}