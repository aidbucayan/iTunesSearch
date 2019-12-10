package com.adrian.bucayan.di.modules

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.adrian.bucayan.ui.viewmodels.authentication.MyViewModelFactory
import com.adrian.bucayan.utils.Pref
import com.adrian.bucayan.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
/**
 * @author Adrian Bucayan
 */

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    /* View Models */
    @Provides
    @Singleton
    fun provideAuthenticationViewModelFactory(
            factory: MyViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)

    @Provides
    @Singleton
    fun providePref(): Pref = Pref(app)

}