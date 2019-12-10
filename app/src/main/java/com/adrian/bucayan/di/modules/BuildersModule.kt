package com.adrian.bucayan.di.modules

import com.adrian.bucayan.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Adrian Bucayan
 */

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}