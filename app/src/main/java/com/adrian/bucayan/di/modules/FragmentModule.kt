package com.adrian.bucayan.di.modules


import com.adrian.bucayan.ui.fragments.BaseFragment
import com.adrian.bucayan.ui.fragments.DetailsFragment
import com.adrian.bucayan.ui.fragments.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Adrian Bucayan
 */

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun detailsFragment(): DetailsFragment

}