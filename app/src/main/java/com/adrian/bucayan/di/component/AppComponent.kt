package com.adrian.bucayan.di.component

import com.adrian.bucayan.Application
import com.adrian.bucayan.di.modules.AppModule
import com.adrian.bucayan.di.modules.BuildersModule
import com.adrian.bucayan.di.modules.FragmentModule
import com.adrian.bucayan.di.modules.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * @author Adrian Bucayan
 */

@Singleton
@Component(
        modules = arrayOf(AndroidInjectionModule::class,
            BuildersModule::class,
            FragmentModule::class,
            AppModule::class,
            NetModule::class)
)
interface AppComponent {
    fun inject(app: Application)
}