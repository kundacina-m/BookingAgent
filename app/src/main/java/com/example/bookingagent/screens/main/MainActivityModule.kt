package com.example.bookingagent.screens.main

import com.example.bookingagent.di.scopes.FragmentScope
import com.example.bookingagent.di.modules.RouteModule
import com.example.bookingagent.screens.home.HomeFragment
import com.example.bookingagent.screens.login.LoginFragment
import com.example.bookingagent.screens.register.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [RouteModule::class])
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeLoginFragmentInjector(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeRegisterFragmentInjector(): RegisterFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeHomeFragmentInjector(): HomeFragment

}