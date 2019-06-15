package com.example.bookingagent.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bookingagent.screens.accommodations.details.AccommodationDetailsFragment
import com.example.bookingagent.screens.accommodations.details.AccommodationDetailsViewModel
import com.example.bookingagent.screens.accommodations.details.edit.AccommodationEditFragment
import com.example.bookingagent.screens.accommodations.details.edit.AccommodationEditViewModel
import com.example.bookingagent.screens.accommodations.list.AccommodationsFragment
import com.example.bookingagent.screens.accommodations.list.AccommodationsViewModel
import com.example.bookingagent.screens.accommodations.add.AddAccommodationFragment
import com.example.bookingagent.screens.accommodations.add.AddAccommodationViewModel
import com.example.bookingagent.screens.home.HomeFragment
import com.example.bookingagent.screens.home.HomeViewModel
import com.example.bookingagent.screens.login.LoginFragment
import com.example.bookingagent.screens.login.LoginViewModel
import com.example.bookingagent.screens.messages.MessagesFragment
import com.example.bookingagent.screens.messages.MessagesViewModel
import com.example.bookingagent.screens.profile.ProfileFragment
import com.example.bookingagent.screens.profile.ProfileViewModel
import com.example.bookingagent.screens.register.RegisterFragment
import com.example.bookingagent.screens.register.RegisterViewModel
import com.example.bookingagent.screens.reservations.list.ReservationsFragment
import com.example.bookingagent.screens.reservations.list.ReservationsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

	@Binds
	internal abstract fun bindViewModelFactory(factory: ViewModelFactory): IViewModelFactory

	@Binds
	@IntoMap
	@ViewModelKey(LoginFragment::class, LoginViewModel::class)
	internal abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(RegisterFragment::class, RegisterViewModel::class)
	internal abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(HomeFragment::class, HomeViewModel::class)
	internal abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(
		AccommodationsFragment::class, AccommodationsViewModel::class)
	internal abstract fun bindAccommodationsViewModel(accommodationsViewModel: AccommodationsViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(AddAccommodationFragment::class, AddAccommodationViewModel::class)
	internal abstract fun bindAddAccommodationsViewModel(
		addAccommodationViewModel: AddAccommodationViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(MessagesFragment::class, MessagesViewModel::class)
	internal abstract fun bindMessagesViewModel(messagesViewModel: MessagesViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(ReservationsFragment::class, ReservationsViewModel::class)
	internal abstract fun bindReservationsViewModel(reservationsViewModel: ReservationsViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(ProfileFragment::class, ProfileViewModel::class)
	internal abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(AccommodationDetailsFragment::class, AccommodationDetailsViewModel::class)
	internal abstract fun bindAccommodationDetailsViewModel(
		accommodationDetailsViewModel: AccommodationDetailsViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(AccommodationEditFragment::class, AccommodationEditViewModel::class)
	internal abstract fun bindAccommodationEditViewModel(accommodationEditViewModel: AccommodationEditViewModel): ViewModel

}