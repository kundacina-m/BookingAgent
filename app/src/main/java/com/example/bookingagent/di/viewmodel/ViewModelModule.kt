package com.example.bookingagent.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bookingagent.screens.accommodations.AccommodationsFragment
import com.example.bookingagent.screens.accommodations.AccommodationsViewModel
import com.example.bookingagent.screens.addaccommodation.AddAccommodationFragment
import com.example.bookingagent.screens.addaccommodation.AddAccommodationViewModel
import com.example.bookingagent.screens.home.HomeFragment
import com.example.bookingagent.screens.home.HomeViewModel
import com.example.bookingagent.screens.login.LoginFragment
import com.example.bookingagent.screens.login.LoginViewModel
import com.example.bookingagent.screens.messages.MessagesFragment
import com.example.bookingagent.screens.messages.MessagesViewModel
import com.example.bookingagent.screens.register.RegisterFragment
import com.example.bookingagent.screens.register.RegisterViewModel
import com.example.bookingagent.screens.reservations.ReservationsFragment
import com.example.bookingagent.screens.reservations.ReservationsViewModel
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
    @ViewModelKey(AccommodationsFragment::class, AccommodationsViewModel::class)
    internal abstract fun bindAccommodationsViewModel(accommodationsViewModel: AccommodationsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddAccommodationFragment::class, AddAccommodationViewModel::class)
    internal abstract fun bindAddAccommodationsViewModel(addAccommodationViewModel: AddAccommodationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MessagesFragment::class, MessagesViewModel::class)
    internal abstract fun bindMessagesViewModel(messagesViewModel: MessagesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReservationsFragment::class, ReservationsViewModel::class)
    internal abstract fun bindReservationsViewModel(reservationsViewModel: ReservationsViewModel): ViewModel

}