package com.adrian.bucayan.di.modules


import com.adrian.bucayan.ui.fragments.BaseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Adrian Bucayan
 */

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseFragment(): BaseFragment

}