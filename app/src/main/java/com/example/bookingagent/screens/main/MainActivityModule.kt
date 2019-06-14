package com.example.bookingagent.screens.main

import com.example.bookingagent.di.scopes.FragmentScope
import com.example.bookingagent.di.modules.RouteModule
import com.example.bookingagent.screens.accommodations.details.AccommodationDetailsFragment
import com.example.bookingagent.screens.accommodations.details.edit.AccommodationEditFragment
import com.example.bookingagent.screens.accommodations.list.AccommodationsFragment
import com.example.bookingagent.screens.addaccommodation.AddAccommodationFragment
import com.example.bookingagent.screens.home.HomeFragment
import com.example.bookingagent.screens.login.LoginFragment
import com.example.bookingagent.screens.messages.MessagesFragment
import com.example.bookingagent.screens.profile.ProfileFragment
import com.example.bookingagent.screens.register.RegisterFragment
import com.example.bookingagent.screens.reservations.ReservationsFragment
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

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeAccommodationsFragmentInjector(): AccommodationsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeAddAccommodationFragmentInjector(): AddAccommodationFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeReservationsFragmentInjector(): ReservationsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeMessagesFragmentInjector(): MessagesFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeProfileFragmentInjector(): ProfileFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeAccommodationDetailsFragmentInjector(): AccommodationDetailsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeAccommodationEditFragmentInjector(): AccommodationEditFragment




}