package com.example.bookingagent.di.modules

import com.example.bookingagent.di.scopes.ActivityScope
import com.example.bookingagent.screens.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [BindingFragmentModule::class, BindingRoutesModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity

}