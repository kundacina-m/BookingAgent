package com.example.bookingagent.screens.main

import com.example.bookingagent.di.modules.RouteModule
import com.example.bookingagent.di.scopes.FragmentScope
import com.example.bookingagent.screens.accommodations.add.AddAccommodationFragment
import com.example.bookingagent.screens.accommodations.details.AccommodationDetailsFragment
import com.example.bookingagent.screens.accommodations.details.edit.EditAccommodationFragment
import com.example.bookingagent.screens.rooms.add.AddRoomFragment
import com.example.bookingagent.screens.rooms.details.RoomDetailsFragment
import com.example.bookingagent.screens.rooms.edit.EditRoomFragment
import com.example.bookingagent.screens.rooms.list.RoomsFragment
import com.example.bookingagent.screens.accommodations.list.AccommodationsFragment
import com.example.bookingagent.screens.home.HomeFragment
import com.example.bookingagent.screens.login.LoginFragment
import com.example.bookingagent.screens.messages.MessagesFragment
import com.example.bookingagent.screens.profile.ProfileFragment
import com.example.bookingagent.screens.register.RegisterFragment
import com.example.bookingagent.screens.reservations.details.ReservationDetailsFragment
import com.example.bookingagent.screens.reservations.list.ReservationsFragment
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
	abstract fun contributeAccommodationEditFragmentInjector(): EditAccommodationFragment

	@FragmentScope
	@ContributesAndroidInjector
	abstract fun contributeAddRoomFragmentInjector(): AddRoomFragment

	@FragmentScope
	@ContributesAndroidInjector
	abstract fun contributeRoomDetailsFragmentInjector(): RoomDetailsFragment

	@FragmentScope
	@ContributesAndroidInjector
	abstract fun contributeEditRoomFragmentInjector(): EditRoomFragment

	@FragmentScope
	@ContributesAndroidInjector
	abstract fun contributeRoomsFragmentInjector(): RoomsFragment

	@FragmentScope
	@ContributesAndroidInjector
	abstract fun contributeReservationDetailsFragmentInjector(): ReservationDetailsFragment

}