package com.adrian.bucayan.di.modules

import android.app.Application
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

  /*  @Provides
    @Singleton
    fun provideAuthenticationViewModelFactory(
            factory: AuthenticationViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideRegisterViewModelFactory(
            factory: RegisterViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideTicketViewModelFactory(
            factory: TicketViewModelFactory): ViewModelProvider.Factory = factory*/

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)

    @Provides
    @Singleton
    fun providePref(): Pref = Pref(app)

}